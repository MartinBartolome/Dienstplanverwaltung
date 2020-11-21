package ffhs.students.projects.dienstplanverwaltung;

import ffhs.students.projects.dienstplanverwaltung.database.IShift;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Helper {
    public static final String slotIdDevider = "-";
    public static final DateTimeFormatter dateFormatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static boolean isDayInWeekInBiWeeklyRecurrence(LocalDate day, LocalDate startOfBiWeeklyRecurrence){
        return true; // todo
    }

    public static String stringFromDate(LocalDate date){ return dateFormatter.format(date);  }
    public static LocalDate dateFromString(String dateString){ return LocalDate.parse(dateString,dateFormatter); }


    public static String generateSlotId(ISlot slot, IShift shift){
        long shiftTemplateId = shift.getShiftTemplate().isPresent() ? shift.getShiftTemplate().get().getId() : -1;
        return stringFromDate(shift.getDay()) + slotIdDevider + shiftTemplateId  + slotIdDevider + slot.getSlotType().getTitle();
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
