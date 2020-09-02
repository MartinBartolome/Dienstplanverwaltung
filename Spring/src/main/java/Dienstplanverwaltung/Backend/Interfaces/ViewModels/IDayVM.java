package Dienstplanverwaltung.Backend.Interfaces.ViewModels;

import java.util.List;

public interface IDayVM {
    String getDate();
    List<IShiftVM> getShiftsVM();
    Boolean isCurrentMonth();
}
