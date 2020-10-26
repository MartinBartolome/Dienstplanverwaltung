package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.database.ISlot;

import java.util.List;
import java.util.stream.Collectors;

class SlotVM {
    private final int id;
    private final String title;
    private final List<String> assigned;
    private final List<String> applied;
    private final String slotType;

    public SlotVM(ISlot slot){
        id = slot.getSlotId();
        title = slot.getSlotType().getTitle();
        assigned = slot.getAssigned().stream()
                .map(iEmployee -> iEmployee.getUser().getName())
                .collect(Collectors.toList());
        applied = slot.getApplied().stream()
                .map(iEmployee -> iEmployee.getUser().getName())
                .collect(Collectors.toList());
        slotType = slot.getSlotType()
                .getTitle();
    }

    public int getId() { return id; }
    public String getTitle() {  return title; }
    public List<String> getAssigned() {  return assigned; }
    public List<String> getApplied() {  return applied; }
    public String getSlotType() { return slotType; }
}
