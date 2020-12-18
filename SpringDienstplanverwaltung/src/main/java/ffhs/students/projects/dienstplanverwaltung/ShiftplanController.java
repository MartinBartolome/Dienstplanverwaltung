package ffhs.students.projects.dienstplanverwaltung;

//import ffhs.students.projects.dienstplanverwaltung.database.sql.UserRepository;
import ffhs.students.projects.dienstplanverwaltung.administration.AdministrationManager;
import ffhs.students.projects.dienstplanverwaltung.administration.ResponseInfo;
import ffhs.students.projects.dienstplanverwaltung.administration.SysAdminTenantConfig;
import ffhs.students.projects.dienstplanverwaltung.administration.TableViewData;
import ffhs.students.projects.dienstplanverwaltung.administration.employeesconfig.EmployeeConfig;
import ffhs.students.projects.dienstplanverwaltung.administration.employeesconfig.EmployeesConfig;
import ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig.ShiftPlanConfig;
import ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig.ShiftTemplateConfig;
import ffhs.students.projects.dienstplanverwaltung.database.sql.SqlDatabaseManager;
import ffhs.students.projects.dienstplanverwaltung.shiftplan.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
public class ShiftplanController {

    @CrossOrigin(origins = "*")
    @GetMapping("/shiftPlan")
    public Shiftplan getShiftplan(
            @RequestParam(value = "localId") int localId,
            @RequestParam(value = "employeeName") String employeeName,
            @RequestParam(value = "month", defaultValue = "11.05.2020") String date) {
        LocalDate month =  LocalDate.parse(date,Helper.dateFormatter);
        ShiftPlanManager.databaseManager = dbManager;
        return ShiftPlanManager.GetShiftPlan(month, localId,employeeName);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/assignEmployeeToSlot")
    public SlotVM assignEmployeeToSlot(
            @RequestParam(value = "localId", defaultValue = "1") int localId,
            @RequestParam(value = "employeeName") String employeeName,
            @RequestParam(value = "slotIdString") String slotIdString,
            @RequestParam(value = "isAssigned") boolean isAssigned){

        ShiftPlanManager.databaseManager = dbManager;
        return ShiftPlanManager.addEmployeeToSlot(localId,employeeName,slotIdString,
                isAssigned ? ShiftPlanManager.AddOrRemove.add : ShiftPlanManager.AddOrRemove.remove,
                ShiftPlanManager.AddingType.assign);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/applyEmployeeToSlot")
    public SlotVM applyEmployeeToSlot(
            @RequestParam(value = "localId") int localId,
            @RequestParam(value = "employeeName") String employeeName,
            @RequestParam(value = "slotIdString") String slotIdString,
            @RequestParam(value = "isApplied") boolean isApplied){

        ShiftPlanManager.databaseManager = dbManager;
        return ShiftPlanManager.addEmployeeToSlot(localId,employeeName,slotIdString,
                isApplied ? ShiftPlanManager.AddOrRemove.add : ShiftPlanManager.AddOrRemove.remove,
                ShiftPlanManager.AddingType.apply);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getChooseLocal")
    public TableViewData getChooseLocal(
            @RequestParam(value = "userNickName") String userNickName){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.getChooseLocal(userNickName);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getServiceRoles")
    public TableViewData getServiceRoles(
            @RequestParam(value = "localId") long localId){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.getServiceRoles(localId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/addServiceRole")
    public TableViewData addServiceRole(
            @RequestParam(value = "localId") long localId,
            @RequestParam(value = "title") String title){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.addServiceRole(localId,title);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/updateServiceRole")
    public TableViewData updateServiceRole(
            @RequestParam(value = "serviceRoleId") long serviceRoleId,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "isActive") boolean isActive){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.updateServiceRole(serviceRoleId,title,isActive);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getOwnedLocals")
    public TableViewData getOwnedLocals(
            @RequestParam(value = "userNickName") String userNickName){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.getOwnedLocals(userNickName);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/requestNewLocal")
    public TableViewData requestNewLocal(
            @RequestParam(value = "userNickName") String userNickName,
            @RequestParam(value = "title") String title){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.requestNewLocal(userNickName,title);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/updateLocal")
    public TableViewData updateLocal(
            @RequestParam(value = "localId") long localId,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "isActive") boolean isActive){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.updateLocal(localId,title,isActive);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getSysAdminTenantConfig")
    public SysAdminTenantConfig getSysAdminTenantConfig(){
        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.getSysAdminTenantConfig();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/localSetState")
    public SysAdminTenantConfig localSetState(
            @RequestParam(value = "localId") long localId,
            @RequestParam(value = "isGranted") boolean isGranted,
            @RequestParam(value = "isActive") boolean isActive){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.localSetState(localId,isGranted,isActive);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getEmployeesConfig")
    public EmployeesConfig getEmployeesConfig(
            @RequestParam(value = "employeeName") String employeeName,
            @RequestParam(value = "localId") long localId){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.getEmployeesConfig(localId,employeeName);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/updateEmployeeConfig")
    public EmployeeConfig updateEmployeeConfig(
            @RequestParam(value = "localId") long localId,
            @RequestBody EmployeeConfig employeeConfig){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.updateEmployee(employeeConfig,localId);
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/getShiftPlanConfig")
    public ShiftPlanConfig getShiftPlanConfig(
            @RequestParam(value = "localId") long localId){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.getShiftPlanConfig(localId);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateShiftTemplateConfig")
    public ShiftTemplateConfig updateShiftTemplateConfig(
            @RequestParam(value = "localId") long localId,
            @RequestBody ShiftTemplateConfig shiftTemplateConfig){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.updateShiftTemplateConfig(localId,shiftTemplateConfig);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/inviteUser")
    public boolean inviteUser(
            @RequestParam(value = "userNickName") String userNickName,
            @RequestParam(value = "localId") long localId){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.inviteUser(userNickName,localId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/shiftSetIsCanceled")
    public ShiftVM shiftSetIsCanceled(
            @RequestParam(value = "shiftId") String shiftId,
            @RequestParam(value = "localId") long localId,
            @RequestParam(value = "employeeName") String employeeName,
            @RequestParam(value = "isCanceled") boolean isCanceled){

        ShiftPlanManager.databaseManager = dbManager;
        return ShiftPlanManager.setIsCanceled(shiftId,localId,employeeName,isCanceled);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/registerUser")
    public ResponseInfo registerUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.registerUser(username,password);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/loginUser")
    public ResponseInfo loginUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password){

        AdministrationManager.databaseManager = dbManager;
        return AdministrationManager.loginUser(username,password);
    }

    @Autowired
    public SqlDatabaseManager dbManager;


    @GetMapping("/createFakeData")
    public void test(){

        dbManager.createFakeDate();
    }




}
