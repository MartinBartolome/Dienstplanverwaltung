package ffhs.students.projects.dienstplanverwaltung.database;

import java.util.List;

public interface ISlot extends ISaveable {
    IShift getShift();
    long getSlotId();
    List<IEmployee> getAssigned();
    List<IEmployee> getApplied();
    String getTitle();
    int getNumberOfEmployeesNeeded();
    void applyEmployee(IEmployee employee);
    void assignEmployee(IEmployee employee);
    void unApplyEmployee(IEmployee employee);
    void unAssignEmployee(IEmployee employee);
    List<IServiceRole> getServiceRoles();
    long getTemplateSlotId();
    default String getSlotStringWithNeededEmpl(){
        return getTitle() + " (" + getNumberOfEmployeesNeeded() + ")";
    }
}
