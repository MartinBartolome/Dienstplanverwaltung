package ffhs.students.projects.dienstplanverwaltung.database;

import ffhs.students.projects.dienstplanverwaltung.Helper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IShiftTemplate {
    long getId();
    ILocal getLocal();
    String getTitle();
    RecurrenceType getRecurrence();
    LocalDate getFrom();
    LocalDate getTo();
    List<DayOfWeek> getWeekDays();
    List<ISlot> getSlots();
    LocalTime getFromTime();
    LocalTime getToTime();
    String getLongTitle();
    default boolean isOnDay(LocalDate day){
        if (!day.isAfter(getFrom()) && !day.isEqual(getFrom()))
            return false;
        if (getTo() != null && !day.isBefore(getTo().plusDays(1)))
            return false;

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
