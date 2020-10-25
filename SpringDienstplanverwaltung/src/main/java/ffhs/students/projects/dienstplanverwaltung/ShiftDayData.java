package ffhs.students.projects.dienstplanverwaltung;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ShiftDayData {
    private final LocalDate day;
    private final List<IShift> shifts;

    public ShiftDayData(LocalDate day, List<IShift> shifts) {
        this.day = day;
        this.shifts = shifts;
    }

    public LocalDate getDay() {
        return day;
    }
    public List<IShift> getShifts() { return shifts; }

    public static Optional<IShift> getShiftOnDayForTemplate(LocalDate day, int templateId, List<ShiftDayData> shiftDayDataList){
        return shiftDayDataList.stream()
                .filter(sdd -> sdd.getDay().equals(day))
                .map(sdd -> sdd.getIfExistsForTemplate(templateId))
                .flatMap(Optional::stream)
                .findAny();
    }

    private Optional<IShift> getIfExistsForTemplate(int templateId){
        return getShifts().stream()
                .filter(shift -> shift.getShiftTemplateId() == templateId)
                .findAny();
    }
}


