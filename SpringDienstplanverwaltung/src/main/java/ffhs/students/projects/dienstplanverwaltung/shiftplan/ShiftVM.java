package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.database.IShift;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;

import java.util.List;
import java.util.stream.Collectors;

class ShiftVM {
    private int id;
    private final String title;
    private final boolean isCanceled;
    private final List<SlotVM> slotVMs;

    public ShiftVM(IShift shift){
        //id = shift.getShiftTemplate();
        title = shift.getTitle() + " " +shift.getFromTime() + "-" + shift.getToTime();
        isCanceled = shift.getIsCanceled();
        slotVMs = shift.getSlots().stream()
                .map((ISlot slot) -> new SlotVM(slot,shift))
                .collect(Collectors.toList());
    }

    public int getId() { return id;  }
    public String getTitle() { return title; }
    public boolean isCanceled() { return isCanceled; }
    public List<SlotVM> getSlots() { return slotVMs; }
}
