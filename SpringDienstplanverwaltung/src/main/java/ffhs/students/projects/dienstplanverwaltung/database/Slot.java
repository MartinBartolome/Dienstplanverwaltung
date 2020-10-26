package ffhs.students.projects.dienstplanverwaltung.database;

import ffhs.students.projects.dienstplanverwaltung.database.IEmployee;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;
import ffhs.students.projects.dienstplanverwaltung.database.ISlotType;

import java.util.List;

class Slot implements ISlot {
    private final int id;
    private final List<IEmployee> assigned;
    private final List<IEmployee> applied;
    private final ISlotType slotType;

    @Override
    public int getSlotId() {
        return id;
    }

    @Override
    public List<IEmployee> getAssigned() {
        return assigned;
    }

    @Override
    public List<IEmployee> getApplied() {
        return applied;
    }

    @Override
    public ISlotType getSlotType() {
        return slotType;
    }

    public Slot(int id, List<IEmployee> assigned, List<IEmployee> applied, ISlotType slotType){
        this.id = id;
        this.assigned = assigned;
        this.applied = applied;
        this.slotType = slotType;
    }
}
