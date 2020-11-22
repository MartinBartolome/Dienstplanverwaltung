package ffhs.students.projects.dienstplanverwaltung.database;


import ffhs.students.projects.dienstplanverwaltung.database.sql.ServiceRoleEntity;

import java.util.List;

public interface ILocal {
    long getId();
    List<ISlotType> getSlotTypes();
    IUser getOwner();
    String getTitle();
    List<IServiceRole> getServiceRoles();
    boolean isGranted();
    boolean isActive();
}
