import {Component, Input, OnInit} from '@angular/core';
import {EmployeesConfig} from './Models/EmployeesConfig';
import {EmployeeInviteComponent} from './Components/EmployeeInvite/EmployeeInvite.component';
import {MatDialog} from '@angular/material/dialog';
import {ListItem} from '../General/Models/ListItem';
import {EmployeeConfig} from './Models/EmployeeConfig';
import {EmployeeConfigComponent} from './Components/EmployeeConfig/EmployeeConfig.component';

@Component({
  selector: 'app-employee-configuration',
  templateUrl: './EmployeeConfiguration.component.html',
  styleUrls: ['./EmployeeConfiguration.component.css']
})
export class EmployeeConfigurationComponent implements OnInit {
  @Input() employees: EmployeesConfig;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }
  InviteEmployee(): void{
    this.dialog.open(EmployeeInviteComponent);
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
