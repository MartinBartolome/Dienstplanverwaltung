package Dienstplanverwaltung.Backend.Interfaces;

import java.util.Date;
import java.util.List;

public interface IShift {
    Date getStartTime();
    Date getEndTime();
    List<ISlot> getSlots();
}
