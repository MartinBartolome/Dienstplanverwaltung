import {Component, Input, OnInit} from '@angular/core';
import {EmployeesConfig} from './models/EmployeesConfig';
import {EmployeeInviteComponent} from './components/employee-invite/employee-invite.component';
import {MatDialog} from '@angular/material/dialog';
import {ShiftEditComponent} from '../shift-configuration/components/shift-edit/shift-edit.component';
import {ShiftTemplateConfigs} from '../shift-configuration/models/ShiftTemplateConfigs';
import {ListItem} from '../general/models/ListItem';
import {EmployeeConfig} from './models/EmployeeConfig';
import {EmployeeConfigComponent} from './components/employee-config/employee-config.component';

@Component({
  selector: 'app-employee-configuration',
  templateUrl: './employee-configuration.component.html',
  styleUrls: ['./employee-configuration.component.css']
})
export class EmployeeConfigurationComponent implements OnInit {
  @Input() employees: EmployeesConfig;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }
  inviteemployee(): void{
    const dialogRef = this.dialog.open(EmployeeInviteComponent);
  }
  EditEmployee(item: ListItem): void{
    const dialogRef = this.dialog.open(EmployeeConfigComponent,
      { data: this.employees.employeeConfigs[this.employees.employees.items.indexOf(item)]});
    dialogRef.afterClosed().subscribe((result: EmployeeConfig)  => {
      alert(result.nickName);
      if (result)
      {
      }
    });
  }
}
