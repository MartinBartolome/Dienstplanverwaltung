import { Component, OnInit } from '@angular/core';
import shiftdayimport from '../../SampleData/ShiftDay.json';
import {ShiftDays} from '../../models/ShiftDays';

@Component({
  selector: 'app-shift-day-detail',
  templateUrl: './shift-day-detail.component.html',
  styleUrls: ['./shift-day-detail.component.css']
})
export class ShiftDayDetailComponent implements OnInit {
  /*
    austauschen, wenn es die Daten von wo anders kommen
    @Input() shiftdays: ShiftDays;
  */
  shiftdays: ShiftDays = shiftdayimport;
  constructor() { }

  ngOnInit(): void {
  }

}
