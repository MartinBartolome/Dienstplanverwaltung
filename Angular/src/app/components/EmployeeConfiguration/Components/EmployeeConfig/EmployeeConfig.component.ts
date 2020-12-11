import {Component, Inject, OnInit} from '@angular/core';
import {EmployeeConfig} from '../../Models/EmployeeConfig';
import {ServiceRoleEditComponent} from '../../../ShiftConfiguration/Components/ServiceRoleEdit/ServiceRoleEdit.component';
import {ListItem} from '../../../General/Models/ListItem';
import {DataService} from '../../../../Common/DataService';
import {SharedService} from '../../../../Common/SharedService';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {Table} from '../../../General/Models/Table';

@Component({
  selector: 'app-employee-config',
  templateUrl: './EmployeeConfig.component.html',
  styleUrls: ['./EmployeeConfig.component.css']
})
export class EmployeeConfigComponent implements OnInit {
  constructor(private api: DataService, public globalVariables: SharedService, public dialog: MatDialog,
              public dialogRef: MatDialogRef<EmployeeConfigComponent>,
              @Inject(MAT_DIALOG_DATA) public employee: EmployeeConfig) { }

  ngOnInit(): void {
  }
  newServiceRole(): void{
    const dialogRef = this.dialog.open(ServiceRoleEditComponent, { data: new ListItem()});
    dialogRef.afterClosed().subscribe((result: ListItem ) => {
      this.api.sendGetRequest('/addServiceRole?localId=' + this.globalVariables.getLocalID() + '&title=' + result.title)
        .subscribe((data: Table) => {
          this.employee.serviceRoles = data;
        });
    });
  }

  public editServiceRole(ServiceRole: ListItem): void{
    ServiceRole.selected = !ServiceRole.selected;
    const dialogRef = this.dialog.open(ServiceRoleEditComponent,
      { data: ServiceRole });
    dialogRef.afterClosed().subscribe((result: ListItem ) => {
      this.api.sendGetRequest('/updateServiceRole?serviceRoleId=' + ServiceRole.id + '&title=' + result.title + '&isActive=true')
        .subscribe((data: Table) => {
          this.employee.serviceRoles = data;
        });
    });
  }
}
