package ffhs.students.projects.dienstplanverwaltung.administration.employeesconfig;

import ffhs.students.projects.dienstplanverwaltung.administration.TableViewData;
import ffhs.students.projects.dienstplanverwaltung.database.IEmployee;
import ffhs.students.projects.dienstplanverwaltung.database.ILocal;

public class EmployeeConfig {
    private final String nickName;
    private final TableViewData serviceRoles;
    private final boolean isActive;
    private final double hourlyRate;
    private final String currency;
    private final int monthlyContingent;

    public String getNickName() {  return nickName;  }
    public TableViewData getServiceRoles() { return serviceRoles; }
    public boolean getIsActive() {  return isActive; }
    public double getHourlyRate() { return hourlyRate; }
    public String getCurrency() {  return currency;   }
    public int getMonthlyContingent() {  return monthlyContingent;  }

    public EmployeeConfig(IEmployee employee){
        nickName = employee.getUser().getNickname();
        ILocal local = employee.getLocal();
        serviceRoles = new TableViewData(local.getServiceRoles(),employee.getServiceRoles());
        isActive = employee.isActive();
        hourlyRate = employee.getHourlyRate();
        currency = employee.getCurrency();
        monthlyContingent = employee.getMonthlyContingent();
    }
    public EmployeeConfig(){
        nickName = "";
        serviceRoles = new TableViewData();
        isActive = false;
        hourlyRate = 0.0;
        currency = "CHF";
        monthlyContingent = 0;
    }
}
