package Dienstplanverwaltung.Backend.Controller;

import Dienstplanverwaltung.Backend.Interfaces.ViewModels.IDayVM;
import Dienstplanverwaltung.Backend.Interfaces.ViewModels.IShiftPlanVM;

import java.util.Date;
import java.util.List;

public class ShiftPlanController implements IShiftPlanVM {
    @Override
    public void setMonth(Date month) {

    }

    @Override
    public void nextMonth() {

    }

    @Override
    public void prevMonth() {

    }

    @Override
    public List<IDayVM> getDays() {
        return null;
    }

    @Override
    public String getMonthTitle() {
        return null;
    }
}
