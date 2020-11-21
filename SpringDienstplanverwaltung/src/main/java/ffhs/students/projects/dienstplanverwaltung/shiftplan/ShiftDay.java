package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.Helper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ShiftDay {
    private final String day;
    private final List<ShiftVM> shiftVMs;

    public ShiftDay(ShiftDayData dayData) {
        this.day = Helper.stringFromDate(dayData.getDay());
        this.shiftVMs = dayData.getShifts().stream()
                .map(ShiftVM::new)
                .collect(Collectors.toList());
    }

    public String getDay() {
        return day;
    }
    public List<ShiftVM> getShifts() {  return shiftVMs; }
}
