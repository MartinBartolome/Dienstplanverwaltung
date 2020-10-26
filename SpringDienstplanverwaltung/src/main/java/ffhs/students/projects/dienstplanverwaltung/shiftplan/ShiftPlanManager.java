package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.database.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class ShiftPlanManager {
    public static Shiftplan GetShiftPlan(LocalDate month, int localId){
        List<ShiftDayData> shiftDayDataList = generateShiftDayDatas(month,localId);
        return new Shiftplan(shiftDayDataList,month);
    }

    private static List<ShiftDayData> generateShiftDayDatas(LocalDate month,int localId){
        IDatabaseManager databaseManager = new FakeDataBaseManager();

        List<LocalDate> days = getDaysForMonthView(month);
        LocalDate from = days.get(0);
        LocalDate to = days.get(days.size() -1);

        List<IShift> dbShifts = databaseManager.getShifts(localId,from,to);
        List<IShiftTemplate> shiftTemplates = databaseManager.getShiftTemplates(localId);

        List<ShiftDayData> dbShiftDayDataList = getDbShiftDayDataList(dbShifts);
        return getShiftDayDataList(days,dbShiftDayDataList,shiftTemplates);
    }
    private static List<ShiftDayData> getShiftDayDataList(List<LocalDate> days, List<ShiftDayData> dbShiftDayDataList, List<IShiftTemplate> shiftTemplates) {
        return days.stream()
                .map(day -> getShiftDayData(day,dbShiftDayDataList,shiftTemplates))
                .collect(toList());
    }
    private static ShiftDayData getShiftDayData(LocalDate day,List<ShiftDayData> dbShiftDayDataList, List<IShiftTemplate> shiftTemplates){
        List<IShift> shiftsLikeTemplate = getShiftsLikeTemplateForDay(day,shiftTemplates);
        List<IShift> shiftsOnDay = shiftsLikeTemplate.stream()
                .map(templateShift -> getShiftFromDbOrFromTemplate(day,templateShift,dbShiftDayDataList))
                .collect(toList());
        return new ShiftDayData(day,shiftsOnDay);
    }
    private static IShift getShiftFromDbOrFromTemplate(LocalDate day, IShift templateShift, List<ShiftDayData> dbShiftDayDataList){
        Optional<IShift> dbShift = ShiftDayData.getShiftOnDayForTemplate(day,templateShift.getShiftTemplateId(),dbShiftDayDataList);
        return dbShift.orElse(templateShift);
    }
    private static List<IShift> getShiftsLikeTemplateForDay(LocalDate day, List<IShiftTemplate> shiftTemplates){
        return shiftTemplates.stream()
                .filter(st -> st.isOnDay(day))
                .map(st -> new Shift(st,day))
                .collect(toList());
    }


    /**
     * Transformiert Liste von Schichten in Liste von ShiftDayData.
     * ShiftDayData = Tag + Schichten des Tages
     */
    private static List<ShiftDayData> getDbShiftDayDataList(@org.jetbrains.annotations.NotNull List<IShift> dbShifts){
        Map<LocalDate,List<IShift>> result = dbShifts.stream()
                .collect(groupingBy(IShift::getDay,toList()));
        return result.keySet().stream()
                .map(key -> new ShiftDayData(key,result.get(key)))
                .collect(toList());
    }

    private static List<LocalDate> getDaysForMonthView(LocalDate month) {
        LocalDate firstInMonth = month.withDayOfMonth(1);
        int firstDayWeekDay = firstInMonth.getDayOfWeek().getValue();
        int numberOfDaysBeforeFirstDayInMonth = firstDayWeekDay - 1;
        LocalDate firstDayInView = firstInMonth.minusDays(numberOfDaysBeforeFirstDayInMonth);

        List<LocalDate> result = new ArrayList<>();
        LocalDate iDay = firstDayInView;
        for (int i = 0;i<42;i++){
            result.add(iDay);
            iDay = iDay.plusDays(1);
        }
        return result;
    }
}
