package ffhs.students.projects.dienstplanverwaltung;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Shiftplan {
    private final LocalDate month;
    private final List<ShiftDay> shiftDays;

    public Shiftplan(List<ShiftDayData> shiftDayDataList, LocalDate month){
        this.month = month;
        shiftDays = generateShiftDays(shiftDayDataList);
    }

    public LocalDate getMonth() { return month; }
    public List<ShiftDay> getShiftDays() { return shiftDays;}



    private List<ShiftDay> generateShiftDays(List<ShiftDayData> shiftDayDataList){
        return shiftDayDataList.stream()
                .map(ShiftDay::new)
                .collect(Collectors.toList());
    }
}
