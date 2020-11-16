package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.database.IShift;
import ffhs.students.projects.dienstplanverwaltung.database.IShiftTemplate;

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

    /**
     * Findet in der Liste von Datenbank Schicht-Tagen für ein Template an einem Tag einen Eintrag, falls dieser existiert.
     * @param day Tag an dem die Schicht gesucht wirde
     * @param shiftTemplate Template für das eine Schicht gesucht wird
     * @param shiftDayDataList Die Liste in der gesucht wird
     * @return die gefundene Schicht oder Null, falls keine gefunden wurde.
     */
    public static Optional<IShift> getShiftOnDayForTemplate(LocalDate day, IShiftTemplate shiftTemplate, List<ShiftDayData> shiftDayDataList){
        return shiftDayDataList.stream()
                .filter(sdd -> sdd.getDay().equals(day))
                .map(sdd -> sdd.getIfExistsForTemplate(shiftTemplate))
                .flatMap(Optional::stream)
                .findAny();

    }

    private Optional<IShift> getIfExistsForTemplate(IShiftTemplate shiftTemplate){
        return getShifts().stream()
                .filter(shift -> shift.getShiftTemplate() == shiftTemplate)
                .findAny();
    }
}


