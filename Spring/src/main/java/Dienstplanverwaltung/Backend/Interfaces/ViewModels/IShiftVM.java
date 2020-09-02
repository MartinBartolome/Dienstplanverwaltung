package Dienstplanverwaltung.Backend.Interfaces.ViewModels;

import java.util.List;

public interface IShiftVM {
    String getTitle();
    List<ISlotVM> getSlotsVM();
}
