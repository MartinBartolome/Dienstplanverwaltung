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
    @Override
    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn()
    private LocalEntity local;
    @Override
    public ILocal getLocal() {  return local; }


    private String title;
    @Override
    public String getTitle() {
        return title;
    }




    private RecurrenceType recurrenceType;
    @Override
    public RecurrenceType getRecurrence() {
        return recurrenceType;
    }

    public void setTitle(String title) {  this.title = title;  }
    public void setRecurrenceType(RecurrenceType recurrenceType) { this.recurrenceType = recurrenceType; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }
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
        slotsToDelete.stream()
                .map(this::getSlotWithId)
                .flatMap(Optional::stream)
                .forEach(slot -> slot.delete(repo));
        //create
        slotsToCreate.forEach(slot-> slot.setShiftTemplate(this));
        slotsToCreate.forEach(slotEntity -> slotEntity.save(repo));
        //update
        slotsForUpdate.forEach(newSlot -> {
                    Optional<SlotEntity> slotForUpdate = getSlotWithId(newSlot.getSlotId());
                    slotForUpdate.ifPresent(slot -> slot.update(newSlot,repo));
        });
    }

    private Optional<SlotEntity> getSlotWithId(long id){
        return slots.stream()
                .filter(slot-> slot.getSlotId() == id)
                .findFirst();
    }

    private LocalDate fromDate;
    @Override
    public LocalDate getFrom() { return fromDate; }


    private LocalDate toDate;
    @Override
    public LocalDate getTo() {  return toDate; }

    private boolean isMonday;
    private boolean isTuesday;
    private boolean isWednesday;
    private boolean isThursday;
    private boolean isFriday;
    private boolean isSaturday;
    private boolean isSunday;
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
    public void setWeekdays(List<DayOfWeek> weekDays){
        isMonday = (weekDays.contains(DayOfWeek.MONDAY)) ;
        isTuesday = (weekDays.contains(DayOfWeek.TUESDAY)) ;
        isWednesday = (weekDays.contains(DayOfWeek.WEDNESDAY));
        isThursday = (weekDays.contains(DayOfWeek.THURSDAY));
        isFriday = (weekDays.contains(DayOfWeek.FRIDAY));
        isSaturday = (weekDays.contains(DayOfWeek.SATURDAY));
        isSunday = (weekDays.contains(DayOfWeek.SUNDAY));
    }

    @Override
    public List<ISlot> getSlots() {
        return slots.stream()
                .map(ISlot.class::cast)
                .collect(Collectors.toList());
    }


    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    private LocalTime fromTime;
    @Override
    public LocalTime getFromTime() {
        return fromTime;
    }

    private LocalTime toTime;
    @Override
    public LocalTime getToTime() {
        return toTime;
    }

    @Override
    public boolean equals(IShiftTemplate template) {
        return id == template.getId();
    }

    public String getLongTitle(){
        String result = title;
        result += " - " + Helper.getRecurrenceString(recurrenceType);
        result += " " + getOnDaysString();
        return result;
    }

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
                .reduce((r,day) -> r + ","+day)
                .orElse("").trim();
        result += daysString;//.substring(0,daysString.length() -1);
        result += ")";
        return result;
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

    public void addSlot(SlotEntity slot){
        slots.add(slot); }
}
