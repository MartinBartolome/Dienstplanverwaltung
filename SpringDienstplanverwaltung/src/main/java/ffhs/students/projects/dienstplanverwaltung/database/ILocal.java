package ffhs.students.projects.dienstplanverwaltung.database;

import java.util.List;
import java.util.Optional;

public interface ILocal {
    long getId();
    IUser getOwner();
    String getTitle();
    List<IServiceRole> getServiceRoles();
    boolean isGranted();
    boolean isActive();
    List<IEmployee> getEmployees();
    List<IShiftTemplate> getShiftTemplates();

    default Optional<IServiceRole> getAdminRole(){
        return getServiceRoles().stream()
                .filter(IServiceRole::isAdminRole)
                .findFirst();
    }
}
