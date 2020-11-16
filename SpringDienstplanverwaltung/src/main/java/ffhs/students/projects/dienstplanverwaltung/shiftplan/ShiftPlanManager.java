package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.database.*;
import ffhs.students.projects.dienstplanverwaltung.database.sql.SqlDatabaseManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class ShiftPlanManager {
    static public IDatabaseManager databaseManager = new SqlDatabaseManager();

    //ganzer Schichtplan
    public static Shiftplan GetShiftPlan(LocalDate month, int localId){
        List<ShiftDayData> shiftDayDataList = generateShiftDayDatas(month,localId);
        return new Shiftplan(shiftDayDataList,month);
    }
    public static Optional<ShiftDay> assignEmployeeToSlot(int localId,String employeeName,String slotIdString, boolean isAssigned){
        LocalDate day = Helper.getDateFromSlotId(slotIdString);
        Optional<ISlot> slot = getSlotAndCreateShiftIfNeeded(databaseManager,day,slotIdString,localId);
        Optional<IEmployee> employee = databaseManager.getEmployeeForName(localId,employeeName);
        if (!employee.isPresent() || !slot.isPresent())
            return Optional.empty();

        databaseManager.assignEmployeeToSlot(employee.get(),slot.get(),isAssigned);
        return Optional.of(GetShiftDay(day,localId));
    }
    public static Optional<ShiftDay> applyEmployeeToSlot(int localId,String employeeName,String slotIdString, boolean isApplied){
        LocalDate day = Helper.getDateFromSlotId(slotIdString);
        Optional<ISlot> slot = getSlotAndCreateShiftIfNeeded(databaseManager,day,slotIdString,localId);
        Optional<IEmployee> employee = databaseManager.getEmployeeForName(localId,employeeName);
        if (!employee.isPresent() || !slot.isPresent())
            return Optional.empty();

        databaseManager.applyEmployeeToSlot(employee.get(),slot.get(),isApplied);
        return Optional.of(GetShiftDay(day,localId));
    }

    private static Optional<ISlot> getSlotAndCreateShiftIfNeeded(IDatabaseManager databaseManager, LocalDate day, String slotIdString, int localId) {
        int shiftTemplateId = Helper.getShiftTemplateIdFromSlotId(slotIdString);
        Optional<IShift> shift = databaseManager.getShift(localId,day,shiftTemplateId);
        //erstelle DB Eintrag falls noch kein Eintrag vorhanden
        Optional<IShift> dbShift = shift;
        if (!dbShift.isPresent())
            dbShift = databaseManager.createShift(shiftTemplateId,day);
        if (!dbShift.isPresent())
            return Optional.empty();

        String slotTypeTitle = Helper.getSlotTypeTitleFromSlotId(slotIdString);
        Optional<ISlotType> slotType = databaseManager.getSlotType(localId,slotTypeTitle);
        if (!slotType.isPresent())
            return Optional.empty();

        return databaseManager.getSlotForShiftAndType(dbShift.get(),slotType.get());
    }


    // einzelner Schichttag für Rückgabe bei Änderungen (assign,appply,state)
    private static ShiftDay GetShiftDay(LocalDate day, int localId){
        List<IShift> dbShifts = databaseManager.getShifts(localId,day,day);
        List<ShiftDayData> dbShiftDayDataList = getDbShiftDayDataList(dbShifts);
        List<IShiftTemplate> shiftTemplates = databaseManager.getShiftTemplates(localId);
        ShiftDayData shiftDayData = getShiftDayData(day,dbShiftDayDataList,shiftTemplates);
        return new ShiftDay(shiftDayData);
    }


    /**
     * Erstellt die TagesDaten (ShiftDayData) für alle im Kalender anzuzeigenden Tage.
     * Schichten mit vorhanden Einträgen werden angezeigt.
     * Tage ohne Schichten in DB werden auf Basis der Templates generiert.
     * @param month
     * @param localId
     * @return
     */
    private static List<ShiftDayData> generateShiftDayDatas(LocalDate month,int localId){

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

    /**
     * Findet eine Datenbank Schicht in der übergeben Liste von DatenBank Schichten
     * oder gibt das Template für diesen Tag zurück.
     * @param day gesuchter Tag
     * @param templateShift die gesuchte Schicht an dem dem Tag (zum Template gehörige Schicht)
     * @param dbShiftDayDataList Liste von Datenbank Schichten im Zeitraum
     * @return Datenbankschicht, falls für Template vorhanden - sonst Template
     */
    private static IShift getShiftFromDbOrFromTemplate(LocalDate day, IShift templateShift, List<ShiftDayData> dbShiftDayDataList){
        Optional<IShift> dbShift = ShiftDayData.getShiftOnDayForTemplate(day,templateShift.getShiftTemplate(),dbShiftDayDataList);
        return dbShift.orElse(templateShift);
    }
    private static List<IShift> getShiftsLikeTemplateForDay(LocalDate day, List<IShiftTemplate> shiftTemplates){
        return shiftTemplates.stream()
                .filter(st -> st.isOnDay(day))
                .map(st -> st.shiftForDay(day))
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

    /**
     * Erstellt die 42 Tage (6 Wochen), die im Kalender angezeigt werden
     * @param month - Monat der im Kalender angezeigt wird
     * @return Liste der Tage, die angezeigt werden
     */
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
