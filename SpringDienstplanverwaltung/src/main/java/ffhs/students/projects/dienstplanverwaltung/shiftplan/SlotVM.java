package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.database.IShift;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;

import java.util.List;

class SlotVM {
    private final String id;
    private final String title;
    private final String detailTitle;
    private final List<String> assigned;
    private final List<String> applied;
    //private final String slotType;


    public SlotVM(ISlot slot, IShift shift){
        id = Helper.generateSlotId(slot,shift);
        title = slot.getTitle();
        detailTitle = ((ISlotDisplay) slot).getDetailTitle();
        assigned = ((ISlotDisplay) slot).getAssignedStrings();
        applied = ((ISlotDisplay) slot).getAppliedStrings();
    }

    public String getId() { return id; }
    public String getTitle() {  return title; }
    public String getDetailTitle() {return detailTitle; }
    public List<String> getAssigned() {  return assigned; }
    public List<String> getApplied() {  return applied; }
    //public String getSlotType() { return slotType; }
}
