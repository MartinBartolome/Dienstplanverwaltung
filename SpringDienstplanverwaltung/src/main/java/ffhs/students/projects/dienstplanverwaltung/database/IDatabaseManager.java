package ffhs.students.projects.dienstplanverwaltung.database;

import ffhs.students.projects.dienstplanverwaltung.administration.employeesconfig.EmployeeConfig;
import ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig.ShiftTemplateConfig;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IDatabaseManager {
    // Get
    List<ILocal> getAllLocals();
    Optional<IUser> getUser(String nickName);
    Optional<ILocal> getLocalById(long localID);
    Optional<IShift> getShift(ILocal local, LocalDate day, IShiftTemplate shiftTemplate);
    List<IShift> getShifts(ILocal local, LocalDate from, LocalDate to);
    List<IShiftTemplate> getShiftTemplates(ILocal local);
    Optional<IShiftTemplate> getShiftTemplateById(long localID);
    Optional<IEmployee> getEmployeeForName(ILocal local,String employeeName);
    List<ILocal> getGrantedLocalsForUser(IUser user);
    Optional<IUser> getUserForNicknameAndPassword(String username, String password);
    Optional<ISlot> getSlotForSlotIdAndShift(long slotId,IShift shift);
    List<ILocal> getOwnedLocalsForUser(IUser user);

    // Create
    Optional<IEmployee> createOrUpdateEmployee(EmployeeConfig employeeConfig, ILocal local);
    IShift createShift(IShiftTemplate shiftTemplate, LocalDate day);
    boolean createEmployeeInLocal(IUser user, ILocal local);
    boolean createUserIfNotExist(String username, String password);
    Optional<IShiftTemplate> createOrUpdateShiftTemplate(ILocal local,ShiftTemplateConfig shiftTemplateConfig);
    void requestNewLocal(IUser user, String localName);
    void createSysAdminIfNotExists();

    // Aktualisierungen
    void assignEmployeeToSlot(IEmployee employee, ISlot slot, boolean isAssigned);
    void applyEmployeeToSlot(IEmployee employee, ISlot slot, boolean isApplied);
    void addServiceRole(long localId, String title);
    Optional<IServiceRole> updateServiceRole(long serviceRoleId, String title, boolean isActive);
    Optional<ILocal> updateLocal(long localId, String title, boolean isActive);
    void grantLocal(long localId);
    void localSetState(long localId, boolean isGranted);
    void setIsCanceledForShift(IShift dbShift, boolean isCanceled);
}
