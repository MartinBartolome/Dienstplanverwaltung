package ffhs.students.projects.dienstplanverwaltung;

import java.time.LocalDate;
import java.util.List;

public interface IDatabaseManager {
    List<IShift> getShifts(int localId, LocalDate from, LocalDate to);
    List<IShiftTemplate> getShiftTemplates(int localId);
}
