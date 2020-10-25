package ffhs.students.projects.dienstplanverwaltung;

import java.time.LocalDate;
import java.util.List;

public class Shift implements IShift {
    private final LocalDate day;
    private final String title;
    private final int shiftTemplateId;
    private final boolean isCanceled;
    private final List<ISlot> slots;

    @Override
    public LocalDate getDay() {
        return day;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getShiftTemplateId() {
        return shiftTemplateId;
    }

    @Override
    public boolean getIsCanceled() {
        return isCanceled;
    }

    @Override
    public List<ISlot> getSlots() {
        return slots;
    }

    public Shift(IShiftTemplate template,LocalDate day){
        this.day = day;
        title = template.getTitle();
        shiftTemplateId = template.getId();
        isCanceled = false;
        slots = template.getSlots();
    }

}
