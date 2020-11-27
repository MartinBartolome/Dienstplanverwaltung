import {Component, EventEmitter, Inject, Input, OnInit, Output} from '@angular/core';
import {ShiftDays} from '../../models/ShiftDays';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-shift-day-detail',
  templateUrl: './shift-day-detail.component.html',
  styleUrls: ['./shift-day-detail.component.css']
})
export class ShiftDayDetailComponent implements OnInit {
  @Input() shiftdays: ShiftDays;

  constructor(
    public dialogRef: MatDialogRef<ShiftDayDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ShiftDays) {
    this.shiftdays = data;
  }

  ngOnInit(): void {
  }
}
