package ffhs.students.projects.dienstplanverwaltung.database;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IDatabaseManager {
    List<IShift> getShifts(int localId, LocalDate from, LocalDate to);
    List<IShiftTemplate> getShiftTemplates(int localId);
    List<IEmployee> getEmployees(int localId);
    Optional<IShift> getShift(int localId,LocalDate day, int shiftTemplateId);
    Optional<IShift> createShift(int shiftTemplateId,LocalDate day);
    Optional<ISlotType> getSlotType(int localId, String title);
    Optional<ISlot> getSlotForShiftAndType(IShift shift,ISlotType slotType);
    int assignEmployeeToSlot(IEmployee employee, ISlot slot, boolean isAssigned);
    Optional<IEmployee> getEmployeeForName(int localId,String employeeName);
    int applyEmployeeToSlot(IEmployee employee, ISlot slot, boolean isApplied);
    Optional<IShiftTemplate> getShiftTemplate(int id);
}
