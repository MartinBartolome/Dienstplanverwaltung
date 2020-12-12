package ffhs.students.projects.dienstplanverwaltung;

import ffhs.students.projects.dienstplanverwaltung.database.IShift;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;
import ffhs.students.projects.dienstplanverwaltung.database.RecurrenceType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public abstract class Helper {
    public static final String slotIdDevider = "-";
    public static final DateTimeFormatter dateFormatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static boolean isDayInWeekInBiWeeklyRecurrence(LocalDate day, LocalDate startOfBiWeeklyRecurrence){
        return true; // todo
    }
    public static boolean isDayInMonth(LocalDate day,LocalDate month){
        return day.getMonth() == month.getMonth() && day.getYear() == month.getYear();
    }

    public static String stringFromDate(LocalDate date){
        if (date == null)
            return "";
        return dateFormatter.format(date);  }
    public static LocalDate dateFromString(String dateString){
        if (dateString.isEmpty())
            return LocalDate.now();
        return LocalDate.parse(dateString,dateFormatter);
    }


    public static String generateSlotId(ISlot slot, IShift shift){
        long shiftTemplateId = shift.getShiftTemplate().isPresent() ? shift.getShiftTemplate().get().getId() : -1;
        return stringFromDate(shift.getDay()) + slotIdDevider + shiftTemplateId  + slotIdDevider + slot.getSlotType().getTitle();
    }
    public static String getRecurrenceString(RecurrenceType recurrenceType){
        switch (recurrenceType){
            case Single: return "einmalig";
            case Weekly: return  "wöchentlich";
            case BiWeekly:  return "zwei wöchentlich";
            case Monthly:  return "monatlich";
        }
        return "";
    }
    public static RecurrenceType getRecurrenceType(String recurrenceString){
        if (recurrenceString.isEmpty())
            return null;

        switch (recurrenceString){
            case "einmalig": return RecurrenceType.Single;
            case "wöchentlich": return RecurrenceType.Weekly;
            case "zwei wöchentlich":  return RecurrenceType.BiWeekly;
            case "monatlich":  return RecurrenceType.Monthly;
        }
        return null;
    }

    public static String getDayString(DayOfWeek dayOfWeek){
        switch (dayOfWeek){
            case MONDAY:  return "Montag";
            case TUESDAY:  return "Dienstag";
            case WEDNESDAY: return "Mittwoch";
            case THURSDAY:  return  "Donnerstag";
            case FRIDAY: return "Freitag";
            case SATURDAY: return "Samstag";
            case SUNDAY: return "Sonntag";
        }
        return "getDayString Fehler";
    }
    public static List<DayOfWeek> weekDayList(){
        return Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY,DayOfWeek.SATURDAY,DayOfWeek.SUNDAY);
    }
    public static DayOfWeek getWeekDay(String dayOfWeekString){
        if (dayOfWeekString.isEmpty())
            return null;

        switch (dayOfWeekString){
            case "Montag":  return DayOfWeek.MONDAY;
            case "Dienstag":  return DayOfWeek.TUESDAY;
            case "Mittwoch": return DayOfWeek.WEDNESDAY;
            case "Donnerstag":  return DayOfWeek.THURSDAY;
            case "Freitag": return DayOfWeek.FRIDAY;
            case "Samstag": return DayOfWeek.SATURDAY ;
            case "Sonntag": return DayOfWeek.SUNDAY;
        }
        return null;
    }


    public static LocalDate getDateFromSlotId(String slotId){
        String dateString = slotId.split(slotIdDevider)[0];
        return dateFromString(dateString);
    }
    public static int getShiftTemplateIdFromSlotId(String slotId){
        String shiftTemplateId = slotId.split(slotIdDevider)[1];
        return Integer.parseInt(shiftTemplateId);
    }
    public static String getSlotTypeTitleFromSlotId(String slotId){
        return slotId.split(slotIdDevider)[2];
    }
}
