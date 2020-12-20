import { Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ListItem} from '../../../General/Models/ListItem';
import {ShiftTemplateConfigs} from '../../Models/ShiftTemplateConfigs';
import {SlotEditComponent} from '../SlotEdit/SlotEdit.component';
import {SlotInfo} from '../../Models/SlotInfo';
import {DataService} from '../../../../Common/DataService';
import {SharedService} from '../../../../Common/SharedService';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';

@Component({
  selector: 'app-shift-edit',
  templateUrl: './ShiftEdit.component.html',
  styleUrls: ['./ShiftEdit.component.css'],
})
export class ShiftEditComponent implements OnInit {
  form: FormGroup = new FormGroup({
    Titel: new FormControl('', [ Validators.required]),
    fromdate: new FormControl('', [Validators.required]),
    todate: new FormControl('', ),
    fromtime: new FormControl('', [Validators.required]),
    totime: new FormControl('', [Validators.required]),
  }, { validators: TimebeforeValidator});

  constructor(public dialogRef: MatDialogRef<SlotEditComponent>,
              @Inject(MAT_DIALOG_DATA) public template: ShiftTemplateConfigs,
              public dialog: MatDialog, private api: DataService, public globalVariables: SharedService) {
  }

  ngOnInit(): void {
    this.form.patchValue({Titel: this.template.title});
    this.form.patchValue({fromdate: this.stringToUSDate(this.template.fromDate)});
    this.form.patchValue({todate: this.stringToUSDate(this.template.toDate)});
    this.form.patchValue({fromtime: this.template.startTime});
    this.form.patchValue({totime: this.template.endTime });
  }
  public newSlot(): void {
    const dialogRef = this.dialog.open(SlotEditComponent, {data: new SlotInfo()});
    dialogRef.afterClosed().subscribe((result: SlotInfo) => {
      if (result) {
        this.template.slotInfos.push(result);
        const newli = new ListItem();
        newli.title = result.title + ' (' + result.numberOfEmployeesNeeded  + ')';
        this.template.slots.items.push(newli);
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
        slot.title = result.title + ' (' + result.numberOfEmployeesNeeded  + ')';
      }
    });
  }

  public submit(): void{
    if (this.form.valid) {
      this.template.fromDate = this.DateToString(this.form.get('fromdate').value);
      this.template.toDate = this.DateToString(this.form.get('todate').value);
      this.template.startTime = this.form.get('fromtime').value;
      this.template.endTime = this.form.get('totime').value;
      this.template.title = this.form.get('Titel').value;
      this.dialogRef.close(this.template);
    }
  }
  stringToUSDate(DateString: string): Date
  {
    try {
      return new Date(DateString.split('.', 3)[1].toString() + '/' +
        DateString.split('.', 3)[0].toString() + '/' +
        DateString.split('.', 3)[2].toString());
    }
    catch (error){
      return null;
    }
  }
  DateToString(date: Date): string
  {
    try {
      return date.getDate().toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false })
        + '.' + (date.getMonth() + 1).toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false})
        + '.' + date.getFullYear();
    }
    catch (error){
      return '';
    }
  }
  get getfromtime(): AbstractControl { return this.form.get('fromtime'); }
  get gettotime(): AbstractControl { return this.form.get('totime'); }

  onTimeInput(): void {
    if (this.form.hasError('TimeInvalid')) {
      this.gettotime.setErrors([{TimeInvalid: true}]);
      this.getfromtime.setErrors([{TimeInvalid: true}]);
    }
    else {
      this.gettotime.setErrors(null);
      this.getfromtime.setErrors(null);
    }
  }
}
export const TimebeforeValidator: ValidatorFn = (formGroup: FormGroup): ValidationErrors | null => {
  if (formGroup.get('fromtime').value < formGroup.get('totime').value) {
    return null;
  }
  else {
    return { TimeInvalid: true};
  }
};
