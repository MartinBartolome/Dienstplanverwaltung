import {Component, Inject, Input, OnInit} from '@angular/core';
import {EmployeeConfig} from '../../models/EmployeeConfig';
import {ServiceRoleEditComponent} from '../../../shift-configuration/components/service-role-edit/service-role-edit.component';
import {ListItem} from '../../../../models/ListItem';
import {DataService} from '../../../../common/DataService';
import {SharedService} from '../../../../common/SharedService';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ShiftTemplateConfigs} from '../../../shift-configuration/models/ShiftTemplateConfigs';

@Component({
  selector: 'app-employee-config',
  templateUrl: './employee-config.component.html',
  styleUrls: ['./employee-config.component.css']
})
export class EmployeeConfigComponent implements OnInit {
  constructor(private api: DataService, public globalvariables: SharedService, public dialog: MatDialog,
              public dialogRef: MatDialogRef<EmployeeConfigComponent>,
              @Inject(MAT_DIALOG_DATA) public employee: EmployeeConfig) { }

  ngOnInit(): void {
  }
  newServiceRole(): void{
    const dialogRef = this.dialog.open(ServiceRoleEditComponent, { data: new ListItem()});
    dialogRef.afterClosed().subscribe(result => {
      this.api.sendGetRequest('/addServiceRole?localId=' + this.globalvariables.getLocalID() + '&title=' + result.title)
        .subscribe((data: any) => {
          this.employee.serviceRoles = data;
          console.log(data);
        });
    });
  }

  public editServiceRole(ServiceRole: ListItem): void{
    ServiceRole.selected = !ServiceRole.selected;
    const dialogRef = this.dialog.open(ServiceRoleEditComponent,
      { data: ServiceRole });
    dialogRef.afterClosed().subscribe(result => {
      this.api.sendGetRequest('/updateServiceRole?serviceRoleId=' + ServiceRole.id + '&title=' + result.title + '&isActive=true')
        .subscribe((data: any) => {
          this.employee.serviceRoles = data;
          console.log(data);
        });
    });
  }
}
