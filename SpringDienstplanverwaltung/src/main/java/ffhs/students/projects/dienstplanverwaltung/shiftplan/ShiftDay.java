package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

class ShiftDay {
    private final LocalDate day;
    private final List<ShiftVM> shiftVMs;

    public ShiftDay(ShiftDayData dayData) {
        this.day = dayData.getDay();
        this.shiftVMs = dayData.getShifts().stream()
                .map(ShiftVM::new)
                .collect(Collectors.toList());
    }

    public LocalDate getDay() {
        return day;
    }
    public List<ShiftVM> getShifts() {  return shiftVMs; }
}
