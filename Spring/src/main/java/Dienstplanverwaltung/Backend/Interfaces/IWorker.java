package Dienstplanverwaltung.Backend.Interfaces;

import Dienstplanverwaltung.Backend.Enums.TimeContingentType;
import Dienstplanverwaltung.Backend.Enums.WorkerRole;

import java.util.List;

public interface IWorker {
    String getFirstName();
    String getLastName();
    String getEmail();
    List<WorkerRole> getRoles();
    TimeContingentType getTimeContType();
    Double getTimeContValue();
    Boolean isAcive();
}
