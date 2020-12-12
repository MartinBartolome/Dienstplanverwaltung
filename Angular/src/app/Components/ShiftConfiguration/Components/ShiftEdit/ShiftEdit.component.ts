import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ListItem} from '../../../General/Models/ListItem';
import {ShiftTemplateConfigs} from '../../Models/ShiftTemplateConfigs';
import {SlotEditComponent} from '../SlotEdit/SlotEdit.component';

@Component({
  selector: 'app-shift-edit',
  templateUrl: './ShiftEdit.component.html',
  styleUrls: ['./ShiftEdit.component.css']
})
export class ShiftEditComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ShiftEditComponent>,
              @Inject(MAT_DIALOG_DATA) public template: ShiftTemplateConfigs, public dialog: MatDialog) {
  }

  ngOnInit(): void {
  }

  newSlot(): void{
    const dialogRef = this.dialog.open(SlotEditComponent, { data: new ShiftTemplateConfigs()});
    dialogRef.afterClosed().subscribe(() => {
      alert('Now do the Edit!');
    });
  }

  public editSlot(slot: ListItem): void{
    slot.selected = !slot.selected;
    const dialogRef = this.dialog.open(SlotEditComponent,
      { data: this.template.slotInfos[this.template.slots.items.indexOf(slot)]});
    dialogRef.afterClosed().subscribe(() => {
      alert('Now do the Edit!');
    });
  }
}
