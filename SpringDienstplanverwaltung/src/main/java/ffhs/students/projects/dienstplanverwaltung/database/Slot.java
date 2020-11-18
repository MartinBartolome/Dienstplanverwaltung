package ffhs.students.projects.dienstplanverwaltung.database;
import java.util.List;


class Slot implements ISlotDisplay {
    private final int id;
    private final List<IEmployee> assigned;
    private final List<IEmployee> applied;
    private final ISlotType slotType;
    private final int numberOfEmployeesNeeded;
    private IShift shift;

    @Override
    public IShift getShift() {  return shift; }
    @Override
    public long getSlotId() {
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
    @Override
    public int getNumberOfEmployeesNeeded() { return numberOfEmployeesNeeded; }

    public Slot(int id, IShift shift, List<IEmployee> assigned, List<IEmployee> applied, ISlotType slotType, int numberOfEmployeesNeeded){
        this.id = id;
        this.shift = shift;
        this.assigned = assigned;
        this.applied = applied;
        this.slotType = slotType;
        this.numberOfEmployeesNeeded = numberOfEmployeesNeeded;
    }
}
