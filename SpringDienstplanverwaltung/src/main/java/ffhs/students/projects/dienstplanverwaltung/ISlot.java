package ffhs.students.projects.dienstplanverwaltung;


import java.util.List;

public interface ISlot {
    int getSlotId();
    List<IEmployee> getAssigned();
    List<IEmployee> getApplied();
    ISlotType getSlotType();
}
