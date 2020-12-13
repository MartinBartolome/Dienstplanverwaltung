package ffhs.students.projects.dienstplanverwaltung.administration;

import ffhs.students.projects.dienstplanverwaltung.administration.employeesconfig.EmployeeConfig;
import ffhs.students.projects.dienstplanverwaltung.administration.employeesconfig.EmployeesConfig;
import ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig.ShiftPlanConfig;
import ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig.ShiftTemplateConfig;
import ffhs.students.projects.dienstplanverwaltung.database.*;
import ffhs.students.projects.dienstplanverwaltung.database.sql.*;

import javax.swing.text.html.Option;
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

    public static SysAdminTenantConfig localSetState(long localId, boolean isGranted, boolean isActive){
        databaseManager.localSetState(localId,isGranted,isActive);
        return getSysAdminTenantConfig();
    }

    // Employees
    public static EmployeesConfig getEmployeesConfig(long localId){
        Optional<ILocal> local = databaseManager.getLocalById(localId);
        return local
                .map(EmployeesConfig::new)
                .orElseGet(EmployeesConfig::new);
    }
    public static EmployeeConfig updateEmployee(EmployeeConfig employeeConfig,long localId){
        Optional<ILocal> local = databaseManager.getLocalById(localId);
        if (!local.isPresent())
            return new EmployeeConfig();

        Optional<IEmployee> employee = databaseManager.createOrUpdateEmployee(employeeConfig,local.get());
        return employee
                .map(EmployeeConfig::new)
                .orElseGet(EmployeeConfig::new);
    }

    public static ShiftPlanConfig getShiftPlanConfig(long localId){
        Optional<ILocal> local = databaseManager.getLocalById(localId);
        return local
                .map(ShiftPlanConfig::new)
                .orElseGet(ShiftPlanConfig::new);
    }

    public static ShiftTemplateConfig updateShiftTemplateConfig(long localId, ShiftTemplateConfig shiftTemplateConfig){
        Optional<ILocal> local = databaseManager.getLocalById(localId);
        if (!local.isPresent())
            return new ShiftTemplateConfig();

        Optional<IShiftTemplate> shiftTemplate = databaseManager.createOrUpdateShiftTemplate(local.get(),shiftTemplateConfig);
        return shiftTemplate
                .map(iShiftTemplate -> new ShiftTemplateConfig(iShiftTemplate, local.get().getServiceRoles()))
                .orElseGet(ShiftTemplateConfig::new);
    }

    public static boolean invitateUser(String userNickName, long localId) {

        Optional<ILocal> local = databaseManager.getLocalById(localId);
        if (!local.isPresent())
            return false;

        Optional<IEmployee> employee = databaseManager.getEmployeeForName(local.get(),userNickName);
        if (employee.isPresent())
            return false; // User ist bereits Mitarbeiter

        Optional<IUser> user = databaseManager.getUser(userNickName);
        if (!user.isPresent())
            return false;

        return databaseManager.createEmployeeInLocal(user.get(),local.get());
    }
}
