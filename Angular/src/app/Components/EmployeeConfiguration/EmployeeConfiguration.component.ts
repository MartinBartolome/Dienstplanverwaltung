import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {EmployeesConfig} from './Models/EmployeesConfig';
import {EmployeeInviteComponent} from './Components/EmployeeInvite/EmployeeInvite.component';
import {MatDialog} from '@angular/material/dialog';
import {ListItem} from '../General/Models/ListItem';
import {EmployeeConfig} from './Models/EmployeeConfig';
import {EmployeeConfigComponent} from './Components/EmployeeConfig/EmployeeConfig.component';
import {DataService} from '../../Common/DataService';
import {SharedService} from '../../Common/SharedService';

@Component({
  selector: 'app-employee-configuration',
  templateUrl: './EmployeeConfiguration.component.html',
  styleUrls: ['./EmployeeConfiguration.component.css']
})
export class EmployeeConfigurationComponent implements OnInit {
  @Input() employees: EmployeesConfig;
  @Output() DataChanged = new EventEmitter();

  constructor(public dialog: MatDialog, private api: DataService, public globalVariables: SharedService) { }

  ngOnInit(): void {
  }
  InviteEmployee(): void{
    const dialogRef = this.dialog.open(EmployeeInviteComponent);
    dialogRef.afterClosed().subscribe((result: string)  => {
      if (result)
      {
        this.api.sendGetRequest('/inviteUser?userNickName=' + result + '&localId=' + this.globalVariables.getLocalID())
          .subscribe((data: boolean) => {
          if (data)
          {
            alert('Mitarbeiter erfolgreich eingeladen');
            this.DataChanged.emit();
          }
          else {
            alert('Mitarbeiter nicht gefunden');
          }
        });
      }
    });
  }
  EditEmployee(item: ListItem): void{
    const dialogRef = this.dialog.open(EmployeeConfigComponent,
      { data: this.employees.employeeConfigs[this.employees.employees.items.indexOf(item)]});
    dialogRef.afterClosed().subscribe((result: EmployeeConfig)  => {
      if (result)
      {
        this.api.sendPostRequest('/updateEmployeeConfig?localId=' + this.globalVariables.getLocalID(), result)
          .subscribe((data: EmployeeConfig) => {
            this.employees.employeeConfigs[this.employees.employees.items.indexOf(item)] = data;
          });
      }
      this.DataChanged.emit();
    });
  }
}
