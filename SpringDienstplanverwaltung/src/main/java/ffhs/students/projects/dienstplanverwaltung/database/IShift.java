package ffhs.students.projects.dienstplanverwaltung.database;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IShift {
    long getId();
    LocalDate getDay();
    String getTitle();
    IShiftTemplate getShiftTemplate();
    boolean getIsCanceled();
    List<ISlot> getSlots();
    LocalTime getFromTime();
    LocalTime getToTime();
}