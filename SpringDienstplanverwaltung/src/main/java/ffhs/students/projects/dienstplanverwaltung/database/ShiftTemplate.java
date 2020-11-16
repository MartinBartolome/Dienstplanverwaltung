package ffhs.students.projects.dienstplanverwaltung.database;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

class ShiftTemplate implements IShiftTemplate {
    private final List<ISlot> slots;
    private int id;
    private int localId;
    private RecurrenceType recurrenceType;
    private LocalDate from;
    private LocalDate to;
    private List<DayOfWeek> weekDays;
    private String title;
    private LocalTime fromTime;
    private LocalTime toTime;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public ILocal getLocal() {  return null; }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public RecurrenceType getRecurrence() {
        return recurrenceType;
    }
    @Override
    public LocalDate getFrom() {
        return from;
    }
    @Override
    public LocalDate getTo() {
        return to;
    }
    @Override
    public List<DayOfWeek> getWeekDays() {
        return weekDays;
    }
    @Override
    public List<ISlot> getSlots() {
        return slots;
    }
    @Override
    public IShift shiftForDay(LocalDate day) {  return new Shift(this,day);  }
    @Override
    public LocalTime getFromTime() {  return fromTime; }
    @Override
    public LocalTime getToTime() { return toTime; }

    public ShiftTemplate(int id,int localId,String title, LocalTime fromTime, LocalTime toTime,RecurrenceType recurrenceType,LocalDate from, LocalDate to, List<DayOfWeek> weekDays, List<ISlot> slots){
        this.id = id;
        this.localId = localId;
        this.title = title;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.recurrenceType = recurrenceType;
        this.from = from;
        this.to = to;
        this.weekDays = weekDays;
        this.slots = slots;
    }
}
