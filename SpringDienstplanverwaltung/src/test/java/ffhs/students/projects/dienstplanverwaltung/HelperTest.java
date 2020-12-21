package ffhs.students.projects.dienstplanverwaltung;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    @Test
    void isDayInWeekInBiWeeklyRecurrence() {
        // der gleiche Tag
        LocalDate day = LocalDate.of(2020,12,01);
        LocalDate start = LocalDate.of(2020,12,01);

        Assertions.assertTrue(Helper.isDayInWeekInBiWeeklyRecurrence(day,start));

        // eine Woche davor
        Assertions.assertFalse(Helper.isDayInWeekInBiWeeklyRecurrence(day.minusWeeks(1),start));
        // eine Woche danach
        Assertions.assertFalse(Helper.isDayInWeekInBiWeeklyRecurrence(day.plusWeeks(1),start));
        // zwei Wochen danach
        Assertions.assertTrue(Helper.isDayInWeekInBiWeeklyRecurrence(day.plusWeeks(2),start));
        // drei Wochen danach
        Assertions.assertFalse(Helper.isDayInWeekInBiWeeklyRecurrence(day.plusWeeks(3),start));
        // vier Wochen danach
        Assertions.assertTrue(Helper.isDayInWeekInBiWeeklyRecurrence(day.plusWeeks(4),start));
    }

    @Test
    void stringFromDate() {
        LocalDate date = LocalDate.of(2020,12,01);
        String dateString = Helper.stringFromDate(date);
        assertEquals("01.12.2020",dateString);

        date = null;
        dateString = Helper.stringFromDate(date);
        assertEquals("",dateString);
    }

    @Test
    void dateFromString() {
        LocalDate expected = LocalDate.of(2020,12,01);
        String dateString = "01.12.2020";
        Optional<LocalDate> date = Helper.dateFromString(dateString);
        Assertions.assertTrue(date.isPresent());
        assertEquals(expected,date.get());

        dateString = "01.12.2020_";
        date = Helper.dateFromString(dateString);
        Assertions.assertFalse(date.isPresent());

        dateString = null;
        date = Helper.dateFromString(dateString);
        Assertions.assertFalse(date.isPresent());
    }

    @Test
    void getDayString() {
        String MONDAY =  Helper.getDayString(DayOfWeek.MONDAY);
        String TUESDAY =  Helper.getDayString(DayOfWeek.TUESDAY);
        String WEDNESDAY =  Helper.getDayString(DayOfWeek.WEDNESDAY);
        String THURSDAY =  Helper.getDayString(DayOfWeek.THURSDAY);
        String FRIDAY =  Helper.getDayString(DayOfWeek.FRIDAY);
        String SATURDAY =  Helper.getDayString(DayOfWeek.SATURDAY);
        String SUNDAY =  Helper.getDayString(DayOfWeek.SUNDAY);

        assertEquals("Montag",MONDAY);
        assertEquals("Dienstag",TUESDAY);
        assertEquals("Mittwoch",WEDNESDAY);
        assertEquals("Donnerstag",THURSDAY);
        assertEquals("Freitag",FRIDAY);
        assertEquals("Samstag",SATURDAY);
        assertEquals("Sonntag",SUNDAY);

        String error = "getDayString Fehler";
        String nullStr = Helper.getDayString(null);
        assertEquals(error,nullStr);
    }
    @Test
    void weekDayList() {
        List<DayOfWeek> weekdays = Helper.weekDayList();
        assertEquals(7,weekdays.size());

        Assertions.assertTrue(weekdays.contains(DayOfWeek.MONDAY));
        Assertions.assertTrue(weekdays.contains(DayOfWeek.TUESDAY));
        Assertions.assertTrue(weekdays.contains(DayOfWeek.WEDNESDAY));
        Assertions.assertTrue(weekdays.contains(DayOfWeek.THURSDAY));
        Assertions.assertTrue(weekdays.contains(DayOfWeek.FRIDAY));
        Assertions.assertTrue(weekdays.contains(DayOfWeek.SATURDAY));
        Assertions.assertTrue(weekdays.contains(DayOfWeek.SUNDAY));
    }
    @Test
    void getWeekDay() {
        DayOfWeek day = Helper.getWeekDay("Montag");
        assertEquals(DayOfWeek.MONDAY,day);

        day = Helper.getWeekDay("Dienstag");
        assertEquals(DayOfWeek.TUESDAY,day);

        day = Helper.getWeekDay("Mittwoch");
        assertEquals(DayOfWeek.WEDNESDAY,day);

        day = Helper.getWeekDay("Donnerstag");
        assertEquals(DayOfWeek.THURSDAY,day);

        day = Helper.getWeekDay("Freitag");
        assertEquals(DayOfWeek.FRIDAY,day);

        day = Helper.getWeekDay("Samstag");
        assertEquals(DayOfWeek.SATURDAY,day);

        day = Helper.getWeekDay("Sonntag");
        assertEquals(DayOfWeek.SUNDAY,day);

        day = Helper.getWeekDay("xxx");
        assertNull(day);
    }
    @Test
    void stringFromTime() {
        LocalTime time = LocalTime.of(12,25);
        String expected = "12:25";
        assertEquals(expected,Helper.stringFromTime(time));

        time = null;
        assertEquals("",Helper.stringFromTime(time));
    }

    @Test
    void timeFromString() {
        String timeString = "12:25";
        LocalTime expected = LocalTime.of(12,25);
        Optional<LocalTime> time = Helper.timeFromString(timeString);
        Assertions.assertTrue(time.isPresent());
        assertEquals(expected,time.get());

        timeString = "Fehler";
        time = Helper.timeFromString(timeString);
        Assertions.assertFalse(time.isPresent());

        timeString = null;
        time = Helper.timeFromString(timeString);
        Assertions.assertFalse(time.isPresent());
    }
    @Test
    void getDaysForMonthView() {
        LocalDate month = LocalDate.of(2020,5,5);
        List<LocalDate> days = Helper.getDaysForMonthView(month);
        assertEquals(42,days.size());

        LocalDate first = LocalDate.of(2020,4,27);
        LocalDate last = LocalDate.of(2020,6,7);
        assertEquals(first,days.get(0));
        assertEquals(last,days.get(41));
    }



    @Test
    void generateShiftId() {  }

    @Test
    void generateSlotId() {  }

    @Test
    void getRecurrenceString() { }

    @Test
    void getRecurrenceType() { }

    @Test
    void getDateFromSlotId() { }

    @Test
    void getShiftTemplateIdFromSlotId() { }

    @Test
    void getSlotIdFromSlotIdString() { }
}