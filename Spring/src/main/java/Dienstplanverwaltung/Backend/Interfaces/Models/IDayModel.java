package Dienstplanverwaltung.Backend.Interfaces.Models;

import Dienstplanverwaltung.Backend.Interfaces.IShift;

import java.util.Date;
import java.util.List;

public interface IDayModel {
    Date getDate();
    List<IShift> getShifts();
    Boolean isCurrentMonth();
}
