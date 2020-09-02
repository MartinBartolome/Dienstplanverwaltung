package Dienstplanverwaltung.Backend.Interfaces;

import java.util.Date;
import java.util.List;

public interface IDataBaseManager {
    List<IShift> getShifts(Date date);
    List<IShift> getShifts(Date startdate, Date endDate);
    List<IShift> getShifts(IWorker worker);
    void addShift(IShift shift);
    void addShiftTemplate(IShiftTemplate shiftTemplate);
    List<IShiftTemplate> getActiveShifttemplates();
    List<IWorker> getActiveWorker();
    void AddWorker(IWorker worker);
    List<IWorkerSlotRequest> getWorkerSlotRequest(IWorker worker);
    List<IWorkerSlotRequest> getWorkerSlotRequest(ISlot slot);
    void addWorkerSlotRequest(IWorkerSlotRequest workerSlotRequest);
    List<IServiceRole> getServiceRoles();
    void addServiceRole(IServiceRole serviceRole);
}
