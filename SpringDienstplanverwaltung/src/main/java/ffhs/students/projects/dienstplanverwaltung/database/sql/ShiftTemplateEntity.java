package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.database.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table
class ShiftTemplateEntity implements IShiftTemplate,ISaveable,IDeleteable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "shiftTemplate")
    private List<ShiftEntity> shifts;
    @OneToMany(mappedBy = "shiftTemplate")
    private List<SlotEntity> slots;
    @ManyToOne
    @JoinColumn()
    private LocalEntity local;
    private String title;
    private RecurrenceType recurrenceType;
    private LocalDate fromDate;
    private LocalDate toDate;
    private boolean isMonday;
    private boolean isTuesday;
    private boolean isWednesday;
    private boolean isThursday;
    private boolean isFriday;
    private boolean isSaturday;
    private boolean isSunday;
    private LocalTime fromTime;
    private LocalTime toTime;

    // Getter
    @Override public long getId() {
        return id;
    }
    @Override public ILocal getLocal() {  return local; }
    @Override public String getTitle() {
        return title;
    }
    @Override public RecurrenceType getRecurrence() {
        return recurrenceType;
    }
    @Override public LocalDate getFrom() { return fromDate; }
    @Override public LocalDate getTo() {  return toDate; }
    @Override public LocalTime getFromTime() {
        return fromTime;
    }
    @Override public LocalTime getToTime() {
        return toTime;
    }
    @Override
    public List<DayOfWeek> getWeekDays() {
        List<DayOfWeek> result = new ArrayList<>();
        if (isMonday) result.add(DayOfWeek.MONDAY);
        if (isTuesday) result.add(DayOfWeek.TUESDAY);
        if (isWednesday) result.add(DayOfWeek.WEDNESDAY);
        if (isThursday) result.add(DayOfWeek.THURSDAY);
        if (isFriday) result.add(DayOfWeek.FRIDAY);
        if (isSaturday) result.add(DayOfWeek.SATURDAY);
        if (isSunday) result.add(DayOfWeek.SUNDAY);
        return result;
    }
    @Override public List<ISlot> getSlots() {
        return slots.stream()
                .map(ISlot.class::cast)
                .collect(Collectors.toList());
    }
    public String getLongTitle(){
        String result = title;
        result += " - " + Helper.getRecurrenceString(recurrenceType);
        result += " " + getOnDaysString();
        return result;
    }

    // Setter
    public void setTitle(String title) {  this.title = title;  }
    public void setRecurrenceType(RecurrenceType recurrenceType) { this.recurrenceType = recurrenceType; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }
    public void setWeekdays(List<DayOfWeek> weekDays){
        isMonday = (weekDays.contains(DayOfWeek.MONDAY)) ;
        isTuesday = (weekDays.contains(DayOfWeek.TUESDAY)) ;
        isWednesday = (weekDays.contains(DayOfWeek.WEDNESDAY));
        isThursday = (weekDays.contains(DayOfWeek.THURSDAY));
        isFriday = (weekDays.contains(DayOfWeek.FRIDAY));
        isSaturday = (weekDays.contains(DayOfWeek.SATURDAY));
        isSunday = (weekDays.contains(DayOfWeek.SUNDAY));
    }
    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }
    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    // Konstruktoren
    public ShiftTemplateEntity() {
        slots = new ArrayList<>();
        shifts = new ArrayList<>();
    }
    public ShiftTemplateEntity(ILocal local) {
        slots = new ArrayList<>();
        shifts = new ArrayList<>();
        if (local instanceof LocalEntity)
            this.local = (LocalEntity)local;
    }
    public ShiftTemplateEntity(LocalEntity local,
                               String title,
                               LocalTime fromTime,
                               LocalTime toTime,
                               RecurrenceType recurrenceType,
                               LocalDate from,
                               LocalDate to,
                               List<DayOfWeek> weekDays){
        this.local = local;
        this.title = title;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.recurrenceType = recurrenceType;
        this.fromDate = from;
        this.toDate = to;
        this.slots = new ArrayList<>();
        setWeekdays(weekDays);
    }

    // Aktualisierungen
    public void addSlot(SlotEntity slot){ slots.add(slot); }
    public void updateSlots(List<SlotEntity> newSlots,SlotRepository repo) {
        List<Long> newSlotIds = newSlots.stream()
                .map(SlotEntity::getSlotId)
                .collect(Collectors.toList());
        List<Long> slotIds = slots.stream()
                .map(SlotEntity::getSlotId)
                .collect(Collectors.toList());

        // delete, update, create Listen
        List<Long> slotsToDelete = slotIds.stream()
                .filter(id -> !newSlotIds.contains(id))
                .collect(Collectors.toList());
        List<SlotEntity> slotsToCreate = newSlots.stream()
                .filter(slot -> !slotIds.contains(slot.getSlotId()))
                .collect(Collectors.toList());
        List<SlotEntity> slotsForUpdate = newSlots.stream()
                .filter(slot -> slotIds.contains(slot.getSlotId()))
                .collect(Collectors.toList());

        // delete
        slotsToDelete.forEach(slot -> deleteSlot(slot,repo));
        //create
        slotsToCreate.forEach(slot-> slot.getCopyForTemplate(this,repo));
        //update
        slotsForUpdate.forEach(newSlot -> {
                    Optional<SlotEntity> slotForUpdate = getSlotWithId(newSlot.getSlotId());
                    slotForUpdate.ifPresent(slot -> slot.update(newSlot,repo));
        });
    }
    private void deleteSlot(Long slotId, SlotRepository repo){
        Optional<SlotEntity> slot = getSlotWithId(slotId);
        if(!slot.isPresent())
            return;
        slots.remove(slot.get());
        slot.get().delete(repo);
    }
    private Optional<SlotEntity> getSlotWithId(long id){
        return slots.stream()
                .filter(slot-> slot.getSlotId() == id)
                .findFirst();
    }

    //Helper
    private String getOnDaysString(){
        List<String> days = new ArrayList<>();
        if (isMonday) days.add("Mo");
        if (isTuesday) days.add("Di");
        if (isWednesday) days.add("Mi");
        if (isThursday) days.add("Do");
        if (isFriday) days.add("Fr");
        if (isSaturday) days.add("Sa");
        if (isSunday) days.add("So");
        if (days.size() == 0)
            return "";

        String result = "(";
        String daysString = days.stream()
                .reduce((r,day) -> r+day+",")
                .orElse("").trim();
        result += daysString.substring(daysString.length() -1);
        result += ")";
        return result;
    }

    @Override
    public boolean equals(IShiftTemplate template) {
        return id == template.getId();
    }
}
