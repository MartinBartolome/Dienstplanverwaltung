package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.database.IEmployee;
import ffhs.students.projects.dienstplanverwaltung.database.IShift;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;
import ffhs.students.projects.dienstplanverwaltung.database.SlotUserInteraction;

import java.util.List;

public class SlotVM {
    private final String id;
    private final String title;
    private final String detailTitle;
    private final List<String> assigned;
    private final List<String> applied;
    private SlotUserInteraction allowedUserInteraction;

    public SlotVM(ISlot slot, IShift shift, IEmployee employee){
        id = Helper.generateSlotId(slot,shift);
        title = slot.getTitle();
        detailTitle = ((ISlotDisplay) slot).getDetailTitle();
        assigned = ((ISlotDisplay) slot).getAssignedStrings();
        applied = ((ISlotDisplay) slot).getAppliedStrings();
        allowedUserInteraction = employee.getAllowedSlotInteraction(slot);
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDetailTitle() { return detailTitle; }
    public List<String> getAssigned() { return assigned; }
    public List<String> getApplied() { return applied; }
    public SlotUserInteraction getAllowedUserInteraction() { return allowedUserInteraction; }

    public void setAllowedUserInteraction(SlotUserInteraction allowedUserInteraction){
        this.allowedUserInteraction = allowedUserInteraction;
    }
}
