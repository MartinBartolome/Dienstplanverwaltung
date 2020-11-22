package ffhs.students.projects.dienstplanverwaltung.administration;

import ffhs.students.projects.dienstplanverwaltung.database.IDatabaseManager;
import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IServiceRole;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;
import ffhs.students.projects.dienstplanverwaltung.database.sql.SqlDatabaseManager;

import java.util.List;
import java.util.Optional;

public class AdministrationManager {
    static public IDatabaseManager databaseManager = new SqlDatabaseManager();

    public static TableViewData getChooseLocal(String userNickName){
        if (userNickName.isEmpty())
            return new TableViewData();
        Optional<IUser> user = databaseManager.getUser(userNickName);
        if (!user.isPresent())
            return new TableViewData();

        List<ILocal> locals = databaseManager.getLocalsForUser(user.get());
        return new TableViewData(locals,user.get());
    }

    public static TableViewData getServiceRoles(long localId){
        Optional<ILocal> local = databaseManager.getLocalById(localId);
        if (!local.isPresent())
            return new TableViewData();
        List<IServiceRole> serviceRoles = local.get().getServiceRoles();
        return new TableViewData(serviceRoles);
    }

    public static TableViewData addServiceRole(long localId, String title){
        databaseManager.addServiceRole(localId,title);
        return getServiceRoles(localId);
    }

    public static TableViewData updateServiceRole(long serviceRoleId,String title, boolean isActive){
        Optional<IServiceRole> serviceRole = databaseManager.updateServiceRole(serviceRoleId,title,isActive);
        return serviceRole
                .map(iServiceRole -> getServiceRoles(iServiceRole.getLocal().getId()))
                .orElseGet(TableViewData::new);
    }

    public static TableViewData getOwnedLocals(String userNickName){
        if (userNickName.isEmpty())
            return new TableViewData();
        Optional<IUser> user = databaseManager.getUser(userNickName);
        if (!user.isPresent())
            return new TableViewData();

        List<ILocal> locals = databaseManager.getOwnedLocalsForUser(user.get());
        return TableViewData.getForOwnedLocals(locals);
    }

    public static TableViewData requestNewLocal(String userNickName, String localName){
        if (userNickName.isEmpty())
            return new TableViewData();
        Optional<IUser> user = databaseManager.getUser(userNickName);
        if (!user.isPresent())
            return new TableViewData();

        databaseManager.requestNewLocal(user.get(),localName);
        return getOwnedLocals(userNickName);
    }

    public static TableViewData updateLocal(long localId, String title, boolean isActive){
        Optional<ILocal> local = databaseManager.updateLocal(localId,title,isActive);
        return local
                .map(iLocal -> getOwnedLocals(iLocal.getOwner().getNickname()))
                .orElseGet(TableViewData::new);
    }

    public static SysAdminTenantConfig getSysAdminTenantConfig(){
        return new SysAdminTenantConfig(databaseManager.getAllLocals());
    }
}
