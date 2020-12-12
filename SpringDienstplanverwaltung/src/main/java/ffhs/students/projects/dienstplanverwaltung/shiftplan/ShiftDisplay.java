package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.database.IShift;
import ffhs.students.projects.dienstplanverwaltung.database.IShiftTemplate;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShiftDisplay implements IShift {
    private final LocalDate day;
    private final String title;
    private final boolean isCanceled;
    private final List<ISlot> slots;
    private final LocalTime fromTime;
    private final LocalTime toTime;
    private final Optional<IShiftTemplate> shiftTemplate;
    private int id;

    @Override
    public long getId() { return id;}
    @Override
    public LocalDate getDay() {
        return day;
    }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public Optional<IShiftTemplate> getShiftTemplate() {
        return shiftTemplate;
    }
    @Override
    public boolean getIsCanceled() {
        return isCanceled;
    }
    @Override
    public List<ISlot> getSlots() {
        return slots;
    }
    @Override
    public LocalTime getFromTime() { return fromTime; }
    @Override
    public LocalTime getToTime() { return toTime; }



    //für Anzeige - ohne DB-Eintrag
    public ShiftDisplay(IShiftTemplate template, LocalDate day){
        this.id = -1;
        this.day = day;
        title = template.getTitle();
        isCanceled = false;
        slots = template.getSlots().stream().map(ISlot.class::cast).collect(Collectors.toList());
        fromTime = template.getFromTime();
        toTime = template.getToTime();
        shiftTemplate = Optional.of(template);
    }
    //für DB create (Slots werden seperat erstellt)
    public ShiftDisplay(IShiftTemplate template, LocalDate day, List<ISlot> slots, int id){
        this.id = id;
        this.day = day;
        title = template.getTitle();
        isCanceled = false;
        this.slots = slots;
        fromTime = template.getFromTime();
        toTime = template.getToTime();
        shiftTemplate = Optional.of(template);
    }

    public ShiftDisplay(IShift shift){
        this.id = -1;
        this.day = shift.getDay();
        title = shift.getTitle();
        isCanceled = false;
        slots = shift.getSlots().stream().map(ISlot.class::cast).collect(Collectors.toList());
        fromTime = shift.getFromTime();
        toTime = shift.getToTime();
        shiftTemplate = shift.getShiftTemplate();
    }

}
