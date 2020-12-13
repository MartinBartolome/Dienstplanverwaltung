package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.database.IEmployee;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ShiftDay {
    private final String day;
    private final List<ShiftVM> shiftVMs;
    private final boolean isInMonth;

    public ShiftDay(ShiftDayData dayData, IEmployee employee) {
        this.isInMonth = dayData.isInMonth();
        this.day = Helper.stringFromDate(dayData.getDay());
        this.shiftVMs = dayData.getShifts().stream()
                .map(shift -> new ShiftVM(shift,employee))
                .collect(Collectors.toList());
    }

    public String getDay() {
        return day;
    }
    public List<ShiftVM> getShifts() {  return shiftVMs; }
    public boolean isInMonth() { return isInMonth;   }
}
