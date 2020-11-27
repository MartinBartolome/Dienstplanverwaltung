import {Component, Input, OnInit} from '@angular/core';
import {EmployeesConfig} from './models/EmployeesConfig';
import {EmployeeInviteComponent} from './components/employee-invite/employee-invite.component';
import {MatDialog} from '@angular/material/dialog';

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
}
