import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ShiftTemplateConfigs} from '../../models/ShiftTemplateConfigs';
import {SlotInfos} from '../../models/SlotInfos';
import {DataService} from '../../../../common/DataService';
import {Table} from '../../../general/models/Table';
import {SharedService} from '../../../../common/SharedService';
import {ListItem} from '../../../general/models/ListItem';
import {ServiceRoleEditComponent} from '../service-role-edit/service-role-edit.component';

@Component({
  selector: 'app-slot-edit',
  templateUrl: './slot-edit.component.html',
  styleUrls: ['./slot-edit.component.css']
})
export class SlotEditComponent implements OnInit {
  ServiceRoles: Table;

  constructor(public dialogRef: MatDialogRef<SlotEditComponent>,
              @Inject(MAT_DIALOG_DATA) public slotinfos: SlotInfos,
              private api: DataService, public globalvariables: SharedService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.loadServiceRoles();
  }

  public loadServiceRoles(): void{
    this.api.sendGetRequest('/getServiceRoles?localId=' + this.globalvariables.getLocalID()).subscribe((data: any) => {
      this.ServiceRoles = data;
      console.log(data);
    });
  }
  newServiceRole(): void{
    const dialogRef = this.dialog.open(ServiceRoleEditComponent, { data: new ListItem()});
    dialogRef.afterClosed().subscribe(result => {
      this.api.sendGetRequest('/addServiceRole?localId=' + this.globalvariables.getLocalID() + '&title=' + result.title)
        .subscribe((data: any) => {
        this.ServiceRoles = data;
        console.log(data);
      });
    });
  }

  public editServiceRole(ServiceRole: ListItem): void{
    ServiceRole.selected = false;
    const dialogRef = this.dialog.open(ServiceRoleEditComponent,
      { data: ServiceRole });
    dialogRef.afterClosed().subscribe(result => {
      this.api.sendGetRequest('/updateServiceRole?serviceRoleId=' + ServiceRole.id + '&title=' + result.title + '&isActive=true')
        .subscribe((data: any) => {
          this.ServiceRoles = data;
          console.log(data);
        });
    });
  }
}
