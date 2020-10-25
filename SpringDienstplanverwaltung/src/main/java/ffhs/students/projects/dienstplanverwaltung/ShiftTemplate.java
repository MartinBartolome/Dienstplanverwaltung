package ffhs.students.projects.dienstplanverwaltung;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class ShiftTemplate implements IShiftTemplate{
    private final List<ISlot> slots;
    private int id;
    private RecurrenceType recurrenceType;
    private LocalDate from;
    private LocalDate to;
    private List<DayOfWeek> weekDays;
    private String title;

    @Override
    public int getId() {
        return id;
    }

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

    public ShiftTemplate(int id,String title,RecurrenceType recurrenceType,LocalDate from, LocalDate to, List<DayOfWeek> weekDays, List<ISlot> slots){
        this.id = id;
        this.title = title;
        this.recurrenceType = recurrenceType;
        this.from = from;
        this.to = to;
        this.weekDays = weekDays;
        this.slots = slots;
    }
}
