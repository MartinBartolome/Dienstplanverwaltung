import {ChangeDetectorRef, Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ListItem} from '../../../General/Models/ListItem';
import {ShiftTemplateConfigs} from '../../Models/ShiftTemplateConfigs';
import {SlotEditComponent} from '../SlotEdit/SlotEdit.component';
import {SlotInfo} from '../../Models/SlotInfo';
import {DataService} from '../../../../Common/DataService';
import {SharedService} from '../../../../Common/SharedService';

@Component({
  selector: 'app-shift-edit',
  templateUrl: './ShiftEdit.component.html',
  styleUrls: ['./ShiftEdit.component.css'],
})
export class ShiftEditComponent implements OnInit {
  fromdate: Date;
  todate: Date;

  constructor(public dialogRef: MatDialogRef<SlotEditComponent>,
              @Inject(MAT_DIALOG_DATA) public template: ShiftTemplateConfigs,
              public dialog: MatDialog, private api: DataService, public globalVariables: SharedService) {
  }

  ngOnInit(): void {
    this.fromdate = new Date(this.stringToUSDate(this.template.fromDate));
    this.todate = new Date(this.stringToUSDate(this.template.toDate));
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
  public submit(): void{
    this.template.fromDate = this.DateToString(this.fromdate);
    this.template.toDate = this.DateToString(this.todate);
    this.dialogRef.close(this.template);
  }
  stringToUSDate(DateString: string): Date
  {
    return new Date(DateString.split('.', 3)[1].toString() + '/' +
      DateString.split('.', 3)[0].toString() + '/' +
      DateString.split('.', 3)[2].toString());
  }
  DateToString(date: Date): string
  {
    return date.getDate().toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false })
      + '.' + (date.getMonth() + 1).toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false})
      + '.' + date.getFullYear();
  }
}
