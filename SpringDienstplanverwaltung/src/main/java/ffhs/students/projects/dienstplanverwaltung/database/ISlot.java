package ffhs.students.projects.dienstplanverwaltung.database;


import java.util.List;

public interface ISlot {
    IShift getShift();
    long getSlotId();
    List<IEmployee> getAssigned();
    List<IEmployee> getApplied();
    ISlotType getSlotType();
    int getNumberOfEmployeesNeeded();
}
