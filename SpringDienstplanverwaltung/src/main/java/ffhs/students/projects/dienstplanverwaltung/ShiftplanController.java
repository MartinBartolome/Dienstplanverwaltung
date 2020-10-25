package ffhs.students.projects.dienstplanverwaltung;

import ffhs.students.projects.dienstplanverwaltung.shiftplan.ShiftPlanManager;
import ffhs.students.projects.dienstplanverwaltung.shiftplan.Shiftplan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
public class ShiftplanController {


    @GetMapping("/dienstplan")
    public Shiftplan getShiftplan(
            @RequestParam(value = "localId", defaultValue = "0") int localId,
            @RequestParam(value = "month", defaultValue = "2020-11-11") String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate month =  LocalDate.parse(date);
        return ShiftPlanManager.GetShiftPlan(month, localId);
    }

}
