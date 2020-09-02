package Dienstplanverwaltung.Backend.Interfaces.Models;

import java.util.Date;
import java.util.List;

public interface IShiftPlanModel {
    void setMonth(Date month);
    void nextMonth();
    void prevMonth();
    List<IDayModel> getDays();
    Date getMonth();
}
