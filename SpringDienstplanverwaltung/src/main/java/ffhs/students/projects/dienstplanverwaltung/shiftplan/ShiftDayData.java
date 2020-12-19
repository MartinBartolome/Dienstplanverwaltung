package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.database.IShift;
import ffhs.students.projects.dienstplanverwaltung.database.IShiftTemplate;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShiftDayData {
    private final LocalDate day;
    private final List<ShiftDisplay> shifts;
    private boolean isInMonth;
    public ShiftDayData(LocalDate day, List<IShift> shifts) {
        this.day = day;
        this.shifts = shifts.stream().map(ShiftDisplay::new).collect(Collectors.toList());
    }
    public LocalDate getDay() {
        return day;
    }
    public List<ShiftDisplay> getShifts() {
        return shifts.stream()
                .sorted(Comparator.comparing(ShiftDisplay::getFromTime))
                .collect(Collectors.toList());
    }
    public boolean isInMonth() { return isInMonth; }
    public void setInMonth(boolean inMonth) { isInMonth = inMonth;  }
    /**
     * Findet in der Liste von Datenbank Schicht-Tagen für ein Template an einem Tag einen Eintrag, falls dieser existiert.
     * @param day Tag an dem die Schicht gesucht wirde
     * @param shiftTemplate Template für das eine Schicht gesucht wird
     * @param shiftDayDataList Die Liste in der gesucht wird
     * @return die gefundene Schicht oder Null, falls keine gefunden wurde.
     */
    public static Optional<IShift> getShiftOnDayForTemplate(LocalDate day, Optional<IShiftTemplate> shiftTemplate, List<ShiftDayData> shiftDayDataList){
        return shiftDayDataList.stream()
                .filter(sdd -> sdd.getDay().equals(day))
                .map(sdd -> sdd.getIfExistsForTemplate(shiftTemplate))
                .flatMap(Optional::stream)
                .findAny();

    }

    private Optional<IShift> getIfExistsForTemplate(Optional<IShiftTemplate> shiftTemplate){
        if (!shiftTemplate.isPresent())
            return Optional.empty();

        return getShifts().stream().map(IShift.class::cast)
                .filter(shift -> shiftTemplate.equals(shift.getShiftTemplate()) )
                .findAny();
    }
}


