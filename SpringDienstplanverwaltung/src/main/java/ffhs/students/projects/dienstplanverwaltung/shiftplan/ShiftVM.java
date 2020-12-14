package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.database.IEmployee;
import ffhs.students.projects.dienstplanverwaltung.database.IShift;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftVM {
    private String id;
    private final String title;
    private final boolean isCanceled;
    private final List<SlotVM> slotVMs;

    public ShiftVM(IShift shift, IEmployee employee){
        id = Helper.generateShiftId(shift);
        title = shift.getTitle() + " " +shift.getFromTime() + "-" + shift.getToTime();
        isCanceled = shift.getIsCanceled();
        slotVMs = shift.getSlots().stream()
                .map((ISlot slot) -> new SlotVM(slot,shift,employee))
                .collect(Collectors.toList());
    }

    public String getId() { return id;  }
    public String getTitle() { return title; }
    public boolean isCanceled() { return isCanceled; }
    public List<SlotVM> getSlots() { return slotVMs; }
}
