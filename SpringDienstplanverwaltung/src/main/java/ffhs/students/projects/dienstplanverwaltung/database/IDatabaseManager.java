package ffhs.students.projects.dienstplanverwaltung.database;

import ffhs.students.projects.dienstplanverwaltung.administration.employeesconfig.EmployeeConfig;
import ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig.ShiftTemplateConfig;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IDatabaseManager {
    List<IShift> getShifts(ILocal local, LocalDate from, LocalDate to);
    List<IShiftTemplate> getShiftTemplates(ILocal local);
    List<IEmployee> getEmployees(int localId);
    Optional<IEmployee> createOrUpdateEmployee(EmployeeConfig employeeConfig, ILocal local);
    Optional<IShift> getShift(ILocal local, LocalDate day, Optional<IShiftTemplate> shiftTemplate);

    IShift createShift(IShiftTemplate shiftTemplate, LocalDate day);
    Optional<ISlot> getSlotById(long slotId);
    //Optional<ISlotType> getSlotType(ILocal local, String title);
    // Optional<ISlot> getSlotForShiftAndType(IShift shift,ISlotType slotType);
    void assignEmployeeToSlot(IEmployee employee, ISlot slot, boolean isAssigned);
    Optional<IEmployee> getEmployeeForName(ILocal local,String employeeName);
    void applyEmployeeToSlot(IEmployee employee, ISlot slot, boolean isApplied);

    Optional<ILocal> getLocalById(long localID);
    Optional<IShiftTemplate> getShiftTemplateById(long localID);
    List<ILocal> getLocalsForUser(IUser user);
    Optional<IUser> getUser(String nickName);
    void addServiceRole(long localId, String title);
    Optional<IServiceRole> updateServiceRole(long serviceRoleId, String title, boolean isActive);
    List<ILocal> getOwnedLocalsForUser(IUser user);
    ILocal requestNewLocal(IUser user,String localName);
    Optional<ILocal> updateLocal(long localId, String title, boolean isActive);
    List<ILocal> getAllLocals();
    Optional<ILocal> localSetState(long localId, boolean isGranted, boolean isActive);
    Optional<IShiftTemplate> createOrUpdateShiftTemplate(ILocal local,ShiftTemplateConfig shiftTemplateConfig);
    Optional<ISlot> getSlotForSlotIdAndShift(long slotId,IShift shift);
    //Optional<IShiftTemplate> updateShiftTemplate(ShiftTemplateConfig shiftTemplateConfig);

    boolean createEmployeeInLocal(IUser user, ILocal local);
}
