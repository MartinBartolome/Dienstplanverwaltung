package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.*;
import ffhs.students.projects.dienstplanverwaltung.shiftplan.ShiftDisplay;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
class ShiftTemplateEntity implements IShiftTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "shiftTemplate")
    private List<ShiftEntity> shifts;

    @OneToMany(mappedBy = "shiftTemplate")
    private List<SlotEntity> slots;

    public ShiftTemplateEntity() { }

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
    private void setWeekdays(List<DayOfWeek> weekDays){
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
