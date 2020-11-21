package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.database.*;
import ffhs.students.projects.dienstplanverwaltung.database.sql.SqlDatabaseManager;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import java.security.InvalidParameterException;
import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.sql.SQLException;
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
        Optional<ILocal> local = databaseManager.getLocalById(localId);
        if (!local.isPresent())
            return null; //todo

        List<ShiftDayData> shiftDayDataList = generateShiftDayDatas(month,local.get());
        return new Shiftplan(shiftDayDataList,month);
    }

    public enum AddingType{apply, assign}
    public enum AddOrRemove{add, remove}
    public static ShiftDay addEmployeeToSlot(int localId,String employeeName,String slotIdString, AddOrRemove addOrRemove, AddingType addingType) {
        LocalDate day = Helper.getDateFromSlotId(slotIdString);

        //Local
        Optional<ILocal> local = databaseManager.getLocalById(localId);
        if (!local.isPresent())
            return null; // todo

        //Employee
        Optional<IEmployee> employee = databaseManager.getEmployeeForName(local.get(),employeeName);
        if (!employee.isPresent())
            return GetShiftDay(day,local.get());

        //Slot
        Optional<ISlot> slot = getSlotAndCreateShiftIfNeeded(databaseManager,day,slotIdString,local.get());
        if (!slot.isPresent())
            return GetShiftDay(day,local.get());


        if (addingType == AddingType.assign)
            databaseManager.assignEmployeeToSlot(employee.get(),slot.get(),addOrRemove == AddOrRemove.add);
        if (addingType == AddingType.apply)
            databaseManager.applyEmployeeToSlot(employee.get(),slot.get(),addOrRemove == AddOrRemove.add);

        return GetShiftDay(day,local.get());
    }

    private static Optional<ISlot> getSlotAndCreateShiftIfNeeded(IDatabaseManager databaseManager, LocalDate day, String slotIdString, ILocal local) {
        int shiftTemplateId = Helper.getShiftTemplateIdFromSlotId(slotIdString);
        Optional<IShiftTemplate> shiftTemplate = databaseManager.getShiftTemplateById(shiftTemplateId);
        if (!shiftTemplate.isPresent())
            return Optional.empty();

        Optional<IShift> existingShift = databaseManager.getShift(local,day,shiftTemplate);

        //erstelle DB Schicht falls noch kein Eintrag vorhanden
        IShift dbShift = existingShift.orElseGet(() -> databaseManager.createShift(shiftTemplate.get(), day));

        //SlotType finden
        String slotTypeTitle = Helper.getSlotTypeTitleFromSlotId(slotIdString);
        Optional<ISlotType> slotType = databaseManager.getSlotType(local,slotTypeTitle);
        if (!slotType.isPresent())
            return Optional.empty();

        return databaseManager.getSlotForShiftAndType(dbShift,slotType.get());
    }


    // einzelner Schichttag für Rückgabe bei Änderungen (assign,appply,state)
    private static ShiftDay GetShiftDay(LocalDate day, ILocal local){
        List<IShift> dbShifts = databaseManager.getShifts(local,day,day);
        List<ShiftDayData> dbShiftDayDataList = getDbShiftDayDataList(dbShifts);
        List<IShiftTemplate> shiftTemplates = databaseManager.getShiftTemplates(local);
        ShiftDayData shiftDayData = getShiftDayData(day,dbShiftDayDataList,shiftTemplates);
        return new ShiftDay(shiftDayData);
    }


    /**
     * Erstellt die TagesDaten (ShiftDayData) für alle im Kalender anzuzeigenden Tage.
     * Schichten mit vorhanden Einträgen werden angezeigt.
     * Tage ohne Schichten in DB werden auf Basis der Templates generiert.
     * @param month
     * @param local
     * @return
     */
    private static List<ShiftDayData> generateShiftDayDatas(LocalDate month,ILocal local){

        List<LocalDate> days = getDaysForMonthView(month);
        LocalDate from = days.get(0);
        LocalDate to = days.get(days.size() -1);

        List<IShift> dbShifts = databaseManager.getShifts(local,from,to);
        List<IShiftTemplate> shiftTemplates = databaseManager.getShiftTemplates(local);

        List<ShiftDayData> dbShiftDayDataList = getDbShiftDayDataList(dbShifts);
        return getShiftDayDataList(days,dbShiftDayDataList,shiftTemplates);
    }
    private static List<ShiftDayData> getShiftDayDataList(List<LocalDate> days, List<ShiftDayData> dbShiftDayDataList, List<IShiftTemplate> shiftTemplates) {
        return days.stream()
                .map(day -> getShiftDayData(day,dbShiftDayDataList,shiftTemplates))
                .collect(toList());
    }
    private static ShiftDayData getShiftDayData(LocalDate day,List<ShiftDayData> dbShiftDayDataList, List<IShiftTemplate> shiftTemplates){
        List<ShiftDisplay> shiftsLikeTemplate = getShiftsLikeTemplateForDay(day,shiftTemplates);
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
    private static List<ShiftDisplay> getShiftsLikeTemplateForDay(LocalDate day, List<IShiftTemplate> shiftTemplates){
        return shiftTemplates.stream()
                .filter(st -> st.isOnDay(day))
                .map(st -> new ShiftDisplay(st,day))
                .collect(toList());
    }


    /**
     * Transformiert Liste von Schichten in Liste von ShiftDayData.
     * ShiftDayData = Tag + Schichten des Tages
     */
    private static List<ShiftDayData> getDbShiftDayDataList(List<IShift> dbShifts){
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
