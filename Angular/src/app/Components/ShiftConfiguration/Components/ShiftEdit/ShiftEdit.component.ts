import {ChangeDetectorRef, Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ListItem} from '../../../General/Models/ListItem';
import {ShiftTemplateConfigs} from '../../Models/ShiftTemplateConfigs';
import {SlotEditComponent} from '../SlotEdit/SlotEdit.component';
import {SlotInfo} from '../../Models/SlotInfo';
import {DataService} from '../../../../Common/DataService';
import {SharedService} from '../../../../Common/SharedService';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {passwordMatchValidator} from '../../../General/SignUp/SignUp.component';

@Component({
  selector: 'app-shift-edit',
  templateUrl: './ShiftEdit.component.html',
  styleUrls: ['./ShiftEdit.component.css'],
})
export class ShiftEditComponent implements OnInit {
  form: FormGroup = new FormGroup({
    Titel: new FormControl('',[Validators.required]),
    fromdate: new FormControl('', [Validators.required]),
    todate: new FormControl('', [ Validators.required ]),
    fromtime: new FormControl('', [Validators.required]),
    totime: new FormControl('', [Validators.required]),
  });

  constructor(public dialogRef: MatDialogRef<SlotEditComponent>,
              @Inject(MAT_DIALOG_DATA) public template: ShiftTemplateConfigs,
              public dialog: MatDialog, private api: DataService, public globalVariables: SharedService) {
  }

  ngOnInit(): void {
    this.form.get('Titel').setValue(new Date(this.stringToUSDate(this.template.title)));
    this.form.get('fromdate').setValue(new Date(this.stringToUSDate(this.template.fromDate)));
    this.form.get('todate').setValue(new Date(this.stringToUSDate(this.template.toDate)));
    this.form.get('fromtime').setValue(new Date(this.stringToUSDate(this.template.startTime)));
    this.form.get('totime').setValue(new Date(this.stringToUSDate(this.template.endTime)));
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
    if (this.form.valid) {
      this.template.fromDate = this.DateToString(this.form.get('fromdate').value);
      this.template.toDate = this.DateToString(this.form.get('todate').value);
      this.template.toDate = this.form.get('fromtime').value;
      this.template.toDate = this.form.get('totime').value;
      this.template.toDate = this.form.get('Titel').value;
      this.dialogRef.close(this.template);
    }
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
