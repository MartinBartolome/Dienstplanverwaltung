package ffhs.students.projects.dienstplanverwaltung.database;

import java.time.LocalDate;
import java.util.List;

public interface IShift {
    LocalDate getDay();
    String getTitle();
    int getShiftTemplateId();
    boolean getIsCanceled();
    List<ISlot> getSlots();
}
