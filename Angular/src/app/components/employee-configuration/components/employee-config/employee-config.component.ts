import {Component, Input, OnInit} from '@angular/core';
import {EmployeeConfig} from '../../models/EmployeeConfig';
import {ServiceRoleEditComponent} from '../../../shift-configuration/components/service-role-edit/service-role-edit.component';
import {ListItem} from '../../../../models/ListItem';
import {DataService} from '../../../../common/DataService';
import {SharedService} from '../../../../common/SharedService';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-employee-config',
  templateUrl: './employee-config.component.html',
  styleUrls: ['./employee-config.component.css']
})
export class EmployeeConfigComponent implements OnInit {
  @Input() employee: EmployeeConfig;

  constructor(private api: DataService, public globalvariables: SharedService, public dialog: MatDialog) { }

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
  // würde ich in der ansicht nicht verwenden
  public editServiceRole(ServiceRole: ListItem): void{
    ServiceRole.selected = false;
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
