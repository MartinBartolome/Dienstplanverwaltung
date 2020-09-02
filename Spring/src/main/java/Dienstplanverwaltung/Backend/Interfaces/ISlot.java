package Dienstplanverwaltung.Backend.Interfaces;

import java.util.Date;
import java.util.List;

public interface ISlot {
    Date getScheduledStart();
    Date getScheduledEnd();
    ISloteType getSlotType();
    List<IServiceRole> getPossibleServiceRoles();
    void setWorker(IWorker worker);
    IWorker getWorker();
    void setStart(Date start);
    void setEnd(Date end);
    double getWorkDuration();
}
