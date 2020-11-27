import { Component, Input, OnInit } from '@angular/core';

import {ListItem} from '../../../../models/ListItem';
import {EmployeesConfig} from '../../models/EmployeesConfig';
import {EmployeeConfig} from '../../models/EmployeeConfig';
import {EmployeeInviteComponent} from '../employee-invite/employee-invite.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-employees-config',
  templateUrl: './employees-config.component.html',
  styleUrls: ['./employees-config.component.css']
})
export class EmployeesConfigComponent implements OnInit {
  @Input() employees: EmployeesConfig;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  inviteemployee(): void{
    const dialogRef = this.dialog.open(EmployeeInviteComponent);
  }
}
