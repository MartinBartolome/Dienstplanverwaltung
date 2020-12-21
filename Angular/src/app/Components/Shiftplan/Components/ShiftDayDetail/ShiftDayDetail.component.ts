import {Component, Inject, Input, OnInit} from '@angular/core';
import {ShiftDays} from '../../Models/ShiftDays';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-shift-day-detail',
  templateUrl: './ShiftDayDetail.component.html',
  styleUrls: ['./ShiftDayDetail.component.css']
})
export class ShiftDayDetailComponent implements OnInit {
  @Input() ShiftDays: ShiftDays;

  constructor(
    public dialogRef: MatDialogRef<ShiftDayDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ShiftDays) {
    this.ShiftDays = data;
  }

  ngOnInit(): void {
  }
}
