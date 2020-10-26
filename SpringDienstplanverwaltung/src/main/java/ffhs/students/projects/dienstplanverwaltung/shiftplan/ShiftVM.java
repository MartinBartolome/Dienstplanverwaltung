package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.database.IShift;

import java.util.List;
import java.util.stream.Collectors;

class ShiftVM {
    private int id;
    private final String title;
    private final boolean isCanceled;
    private final List<SlotVM> slotVMs;

    public ShiftVM(IShift shift){
        id = shift.getShiftTemplateId();
        title = shift.getTitle();
        isCanceled = shift.getIsCanceled();
        slotVMs = shift.getSlots().stream()
                .map(SlotVM::new)
                .collect(Collectors.toList());
    }

    public int getId() { return id;  }
    public String getTitle() { return title; }
    public boolean isCanceled() { return isCanceled; }
    public List<SlotVM> getSlots() { return slotVMs; }
}
