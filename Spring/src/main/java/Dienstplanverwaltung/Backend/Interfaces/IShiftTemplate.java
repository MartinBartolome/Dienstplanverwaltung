package Dienstplanverwaltung.Backend.Interfaces;

import Dienstplanverwaltung.Backend.Enums.RecurrenceType;

import java.util.Date;
import java.util.List;

public interface IShiftTemplate {
    Date getStartTime();
    Date getEndTime();
    RecurrenceType getRecurrence();
    List<ISlot> getSlots();
    Boolean isActive();
}
