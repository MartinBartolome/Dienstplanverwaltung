package ffhs.students.projects.dienstplanverwaltung.database;

import java.util.List;

public interface ILocal {
    long getId();
    IUser getOwner();
    String getTitle();
    List<IServiceRole> getServiceRoles();
    boolean isGranted();
    boolean isActive();
    List<IEmployee> getEmployees();
    List<IShiftTemplate> getShiftTemplates();
}
