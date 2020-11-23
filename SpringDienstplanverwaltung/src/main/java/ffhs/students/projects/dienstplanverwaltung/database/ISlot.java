package ffhs.students.projects.dienstplanverwaltung.database;


import java.util.List;

public interface ISlot extends ISaveable {
    IShift getShift();
    long getSlotId();
    List<IEmployee> getAssigned();
    List<IEmployee> getApplied();
    ISlotType getSlotType();
    int getNumberOfEmployeesNeeded();
    void applyEmployee(IEmployee employee);
    void assignEmployee(IEmployee employee);
    void unApplyEmployee(IEmployee employee);
    void unAssignEmployee(IEmployee employee);

    default String getSlotStringWithNeededEmpl(){
        return getSlotType().getTitle() + " (" + getNumberOfEmployeesNeeded() + ")";
    }
}
