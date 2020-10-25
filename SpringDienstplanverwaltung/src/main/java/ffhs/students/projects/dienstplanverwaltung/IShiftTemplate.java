package ffhs.students.projects.dienstplanverwaltung;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public interface IShiftTemplate {
    int getId();
    String getTitle();
    RecurrenceType getRecurrence();
    LocalDate getFrom();
    LocalDate getTo();
    List<DayOfWeek> getWeekDays();
    List<ISlot> getSlots();



    default boolean isOnDay(LocalDate day){
        if (getRecurrence() == RecurrenceType.Single){
            return false; //todo
        }
        if (getRecurrence() == RecurrenceType.Monthly){
            return false; //todo
        }
        DayOfWeek dayOfWeek = day.getDayOfWeek();
        boolean isDayInDays = getWeekDays().contains(dayOfWeek);
        if (!isDayInDays)
            return false;
        if (getRecurrence() == RecurrenceType.Weekly){
            return true;
        }
        if (getRecurrence() == RecurrenceType.BiWeekly){
            return Helper.isDayInWeekInBiWeeklyRecurrence(day,getFrom());
        }

        return false;
    }
}
