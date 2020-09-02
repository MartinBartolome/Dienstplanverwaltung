package Dienstplanverwaltung.Backend.Interfaces.ViewModels;

import java.util.List;

public interface ISlotVM {
    String getTile();
    String getAssignedString();
    List<String> getAppliedString();
    void assignWorkers(List<Integer> workerids);
    void toggleApplication(Integer application);
    Boolean isApplicable();
}
