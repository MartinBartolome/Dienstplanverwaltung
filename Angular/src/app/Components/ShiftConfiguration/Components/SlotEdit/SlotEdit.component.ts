import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {SlotInfo} from '../../Models/SlotInfo';
import {DataService} from '../../../../Common/DataService';
import {Table} from '../../../General/Models/Table';
import {SharedService} from '../../../../Common/SharedService';
import {ListItem} from '../../../General/Models/ListItem';
import {ServiceRoleEditComponent} from '../ServiceRoleEdit/ServiceRoleEdit.component';

@Component({
  selector: 'app-slot-edit',
  templateUrl: './SlotEdit.component.html',
  styleUrls: ['./SlotEdit.component.css']
})
export class SlotEditComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<SlotEditComponent>,
              @Inject(MAT_DIALOG_DATA) public slotInfos: SlotInfo,
              private api: DataService, public globalVariables: SharedService, public dialog: MatDialog) { }

  ngOnInit(): void {
    if (this.slotInfos.serviceRoleTable === undefined) {
      this.loadServiceRoles();
    }
    if (this.slotInfos.id === null)
    {
      this.slotInfos.id = -1;
    }
  }

  public loadServiceRoles(): void{
    this.api.sendGetRequest('/getServiceRoles?localId=' + this.globalVariables.getLocalID()).subscribe((data: Table) => {
      this.slotInfos.serviceRoleTable = data;
    });
  }
  newServiceRole(): void{
    const dialogRef = this.dialog.open(ServiceRoleEditComponent, { data: new ListItem()});
    dialogRef.afterClosed().subscribe((result: ListItem) => {
      this.api.sendGetRequest('/addServiceRole?localId=' + this.globalVariables.getLocalID() + '&title=' + result.title)
        .subscribe((data: Table) => {
          this.slotInfos.serviceRoleTable = data;
      });
    });
  }

  public editServiceRole(ServiceRole: ListItem): void{
    ServiceRole.selected = false;
    const dialogRef = this.dialog.open(ServiceRoleEditComponent,
      { data: ServiceRole });
    dialogRef.afterClosed().subscribe((result: ListItem) => {
      this.api.sendGetRequest('/updateServiceRole?serviceRoleId=' + ServiceRole.id + '&title=' + result.title + '&isActive=true')
        .subscribe((data: Table) => {
          this.slotInfos.serviceRoleTable = data;
        });
    });
  }
}
