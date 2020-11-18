package ffhs.students.projects.dienstplanverwaltung;

//import ffhs.students.projects.dienstplanverwaltung.database.sql.UserRepository;
import ffhs.students.projects.dienstplanverwaltung.database.sql.SqlDatabaseManager;
import ffhs.students.projects.dienstplanverwaltung.shiftplan.ShiftDay;
import ffhs.students.projects.dienstplanverwaltung.shiftplan.ShiftPlanManager;
import ffhs.students.projects.dienstplanverwaltung.shiftplan.Shiftplan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
public class ShiftplanController {


    @GetMapping("/shiftPlan")
    public Shiftplan getShiftplan(
            @RequestParam(value = "localId", defaultValue = "1") int localId,
            @RequestParam(value = "month", defaultValue = "11.05.2020") String date) {
        LocalDate month =  LocalDate.parse(date,Helper.dateFormatter);

        ShiftPlanManager.databaseManager = dbManager;
        
        return ShiftPlanManager.GetShiftPlan(month, localId);
    }

    @GetMapping("/assignEmployeeToSlot")
    public ShiftDay assignEmployeeToSlot(
            @RequestParam(value = "localId") int localId,
            @RequestParam(value = "employeeName") String employeeName,
            @RequestParam(value = "slotIdString") String slotIdString,
            @RequestParam(value = "isAssigned") boolean isAssigned){
        return ShiftPlanManager.assignEmployeeToSlot(localId,employeeName,slotIdString,isAssigned).get();
    }

    @GetMapping("/applyEmployeeToSlot")
    public ShiftDay applyEmployeeToSlot(
            @RequestParam(value = "localId") int localId,
            @RequestParam(value = "employeeName") String employeeName,
            @RequestParam(value = "slotIdString") String slotIdString,
            @RequestParam(value = "isAssigned") boolean isAssigned){
        return ShiftPlanManager.applyEmployeeToSlot(localId,employeeName,slotIdString,isAssigned).get();
    }



    @Autowired
    public SqlDatabaseManager dbManager;

    @GetMapping("/createFakeData")
    public void test(){
        dbManager.createFakeDate();
    }



}
