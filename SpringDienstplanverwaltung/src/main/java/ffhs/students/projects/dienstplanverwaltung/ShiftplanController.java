package ffhs.students.projects.dienstplanverwaltung;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;


@RestController
public class ShiftplanController {


    @GetMapping("/dienstplan")
    public Shiftplan getShiftplan(@RequestParam(value = "localId", defaultValue = "0") int localId){
        LocalDate month =  LocalDate.of(2020,11,11);
        return ShiftPlanManager.GetShiftPlan(month, localId);
    }

}
