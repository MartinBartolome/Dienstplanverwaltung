package ffhs.students.projects.dienstplanverwaltung;

//import ffhs.students.projects.dienstplanverwaltung.database.sql.UserRepository;
import ffhs.students.projects.dienstplanverwaltung.administration.AdministrationManager;
import ffhs.students.projects.dienstplanverwaltung.administration.TableViewData;
import ffhs.students.projects.dienstplanverwaltung.database.sql.SqlDatabaseManager;
import ffhs.students.projects.dienstplanverwaltung.shiftplan.ShiftDay;
import ffhs.students.projects.dienstplanverwaltung.shiftplan.ShiftPlanManager;
import ffhs.students.projects.dienstplanverwaltung.shiftplan.Shiftplan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
public class ShiftplanController {

    @CrossOrigin(origins = "*")
    @GetMapping("/shiftPlan")
    public Shiftplan getShiftplan(
            @RequestParam(value = "localId", defaultValue = "1") int localId,
            @RequestParam(value = "month", defaultValue = "11.05.2020") String date) {
        LocalDate month =  LocalDate.parse(date,Helper.dateFormatter);

        ShiftPlanManager.databaseManager = dbManager;
        return ShiftPlanManager.GetShiftPlan(month, localId);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/assignEmployeeToSlot")
    public ShiftDay assignEmployeeToSlot(
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
    public ShiftDay applyEmployeeToSlot(
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


    @Autowired
    public SqlDatabaseManager dbManager;

    @GetMapping("/createFakeData")
    public void test(){
        dbManager.createFakeDate();
    }



}
