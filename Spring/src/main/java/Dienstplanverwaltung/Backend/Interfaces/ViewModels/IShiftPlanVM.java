package Dienstplanverwaltung.Backend.Interfaces.ViewModels;

import java.util.Date;
import java.util.List;

public interface IShiftPlanVM {
    void setMonth(Date month);
    void nextMonth();
    void prevMonth();
    List<IDayVM> getDays();
    String getMonthTitle();
}
