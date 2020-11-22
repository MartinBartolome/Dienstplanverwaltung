package ffhs.students.projects.dienstplanverwaltung.administration.employeesconfig;

import ffhs.students.projects.dienstplanverwaltung.administration.TableViewData;
import ffhs.students.projects.dienstplanverwaltung.database.IEmployee;
import ffhs.students.projects.dienstplanverwaltung.database.ILocal;

class EmployeeConfig {
    private final String nickName;
    private final TableViewData serviceRoles;
    private final boolean isActive;
    private final double hourlyRate;
    private final String currency;
    private final int monthlyContingent;

    public String getNickName() {  return nickName;  }
    public TableViewData getServiceRoles() { return serviceRoles; }
    public boolean isActive() {  return isActive; }
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
}
