package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.Helper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Shiftplan {
    private final String month;
    private final List<ShiftDay> shiftDays;

    public Shiftplan(List<ShiftDayData> shiftDayDataList, LocalDate month){
        this.month = Helper.stringFromDate(month);
        shiftDays = generateShiftDays(shiftDayDataList);
    }

    public String getMonth() { return month; }
    public List<ShiftDay> getShiftDays() { return shiftDays;}



    private List<ShiftDay> generateShiftDays(List<ShiftDayData> shiftDayDataList){
        return shiftDayDataList.stream()
                .map(ShiftDay::new)
                .collect(Collectors.toList());
    }
}
