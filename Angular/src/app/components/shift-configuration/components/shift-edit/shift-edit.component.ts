import {Component, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ListItem} from '../../../../models/ListItem';
import {ShiftTemplateConfigs} from '../../models/ShiftTemplateConfigs';
import {SlotEditComponent} from '../slot-edit/slot-edit.component';

@Component({
  selector: 'app-shift-edit',
  templateUrl: './shift-edit.component.html',
  styleUrls: ['./shift-edit.component.css']
})
export class ShiftEditComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ShiftEditComponent>,
              @Inject(MAT_DIALOG_DATA) public template: ShiftTemplateConfigs, public dialog: MatDialog) {
  }

  ngOnInit(): void {
  }

  newSlot(): void{
    const dialogRef = this.dialog.open(SlotEditComponent, { data: new ShiftTemplateConfigs()});
    dialogRef.afterClosed().subscribe(result => {
      alert('Now do the Edit!');
    });
  }

  public editSlot(slot: ListItem): void{
    slot.selected = !slot.selected;
    const dialogRef = this.dialog.open(SlotEditComponent,
      { data: this.template.slotInfos[this.template.slots.items.indexOf(slot)]});
    dialogRef.afterClosed().subscribe(result => {
      alert('Now do the Edit!');
    });
  }
}
