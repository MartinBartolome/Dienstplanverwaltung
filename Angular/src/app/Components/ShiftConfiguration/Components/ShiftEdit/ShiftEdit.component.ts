import {ChangeDetectorRef, Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ListItem} from '../../../General/Models/ListItem';
import {ShiftTemplateConfigs} from '../../Models/ShiftTemplateConfigs';
import {SlotEditComponent} from '../SlotEdit/SlotEdit.component';
import {SlotInfo} from '../../Models/SlotInfo';
import {DataService} from '../../../../Common/DataService';
import {SharedService} from '../../../../Common/SharedService';
import {ShiftConfiguration} from '../../Models/ShiftConfiguration';

@Component({
  selector: 'app-shift-edit',
  templateUrl: './ShiftEdit.component.html',
  styleUrls: ['./ShiftEdit.component.css']
})
export class ShiftEditComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<SlotEditComponent>,
              @Inject(MAT_DIALOG_DATA) public template: ShiftTemplateConfigs,
              public dialog: MatDialog, private api: DataService, public globalVariables: SharedService, public cRef: ChangeDetectorRef) {
  }

  ngOnInit(): void {
  }

  public newSlot(): void {
    const dialogRef = this.dialog.open(SlotEditComponent, {data: new SlotInfo()});
    dialogRef.afterClosed().subscribe((result: SlotInfo) => {
      if (result) {
        this.template.slotInfos.push(result);
        this.updateSlotData();
      }
    });
  }

  public editSlot(slot: ListItem): void {
    slot.selected = !slot.selected;
    const dialogRef = this.dialog.open(SlotEditComponent,
      {data: this.template.slotInfos[this.template.slots.items.indexOf(slot)]});
    dialogRef.afterClosed().subscribe((result: SlotInfo) => {
      if (result) {
        this.template.slotInfos[this.template.slots.items.indexOf(slot)] = result;
        this.updateSlotData();
      }
    });
  }
  private updateSlotData(): void {
    this.api.sendPostRequest('/updateShiftTemplateConfig?localId=' + this.globalVariables.getLocalID(), this.template)
      .subscribe((data: ShiftTemplateConfigs) => {
        this.template = data;
      });
  }
}
