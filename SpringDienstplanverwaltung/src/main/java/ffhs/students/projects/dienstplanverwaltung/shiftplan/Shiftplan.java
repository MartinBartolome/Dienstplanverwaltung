package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.database.IEmployee;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Shiftplan {
    private final String month;
    private final List<ShiftDay> shiftDays;

    public Shiftplan(List<ShiftDayData> shiftDayDataList, LocalDate month, IEmployee employee){
        this.month = Helper.stringFromDate(month);
        shiftDays = generateShiftDays(shiftDayDataList,employee);
    }

    public String getMonth() { return month; }
    public List<ShiftDay> getShiftDays() { return shiftDays;}



    private List<ShiftDay> generateShiftDays(List<ShiftDayData> shiftDayDataList, IEmployee employee){
        return shiftDayDataList.stream()
                .map(sdd -> new ShiftDay(sdd,employee))
                .collect(Collectors.toList());
    }
}
