import { Component, Input, OnInit } from '@angular/core';

import {Employees} from '../../models/Employees';
import employeesimport from '../../../../SampleData/Employees.json';
import {ListItem} from '../../../../models/ListItem';
import {Employee} from '../../models/Employee';

@Component({
  selector: 'app-employees-config',
  templateUrl: './employees-config.component.html',
  styleUrls: ['./employees-config.component.css']
})
export class EmployeesConfigComponent implements OnInit {
  /*
    austauschen, wenn es die Daten von wo anders kommen
    @Input() employees: Employees;
  */
  employees: Employees = employeesimport;

  constructor() { }

  ngOnInit(): void {
  }

  getEmployeeConfig(emp: ListItem): Employee{
    return this.employees.employeesConfigs.find(x => x.employeeEmail = emp.title);
  }
}
