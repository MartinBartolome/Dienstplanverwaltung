package ffhs.students.projects.dienstplanverwaltung.database;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

class Shift implements IShift {
    private final LocalDate day;
    private final String title;
    private final int shiftTemplateId;
    private final boolean isCanceled;
    private final List<ISlot> slots;
    private final LocalTime fromTime;
    private final LocalTime toTime;
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
    public IShiftTemplate getShiftTemplate() {
        return null;
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
    public Shift(IShiftTemplate template, LocalDate day){
        this.id = -1;
        this.day = day;
        title = template.getTitle();
        shiftTemplateId = template.getId();
        isCanceled = false;
        slots = template.getSlots();
        fromTime = template.getFromTime();
        toTime = template.getToTime();
    }
    //für DB create (Slots werden seperat erstellt)
    public Shift(IShiftTemplate template, LocalDate day,List<ISlot> slots,int id){
        this.id = id;
        this.day = day;
        title = template.getTitle();
        shiftTemplateId = template.getId();
        isCanceled = false;
        this.slots = slots;
        fromTime = template.getFromTime();
        toTime = template.getToTime();
    }



}
