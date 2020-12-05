package ffhs.students.projects.dienstplanverwaltung.administration.employeesconfig;

import ffhs.students.projects.dienstplanverwaltung.administration.TableViewData;
import ffhs.students.projects.dienstplanverwaltung.database.IEmployee;
import ffhs.students.projects.dienstplanverwaltung.database.ILocal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesConfig {
    private final TableViewData employees;
    private final List<EmployeeConfig> employeeConfigs;

    public TableViewData getEmployees() { return employees; }
    public List<EmployeeConfig> getEmployeeConfigs() { return employeeConfigs; }

    public EmployeesConfig(ILocal local){
        List<IEmployee> localEmployees = local.getEmployees();
        employees = TableViewData.getForEmployees(localEmployees);
        employeeConfigs = localEmployees.stream()
                .map(EmployeeConfig::new)
                .collect(Collectors.toList());
    }
    public EmployeesConfig(){
        employees = new TableViewData();
        employeeConfigs = new ArrayList<>();
    }

}
