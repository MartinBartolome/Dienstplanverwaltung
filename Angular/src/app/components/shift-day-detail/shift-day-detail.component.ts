import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ShiftDays} from '../../models/ShiftDays';

@Component({
  selector: 'app-shift-day-detail',
  templateUrl: './shift-day-detail.component.html',
  styleUrls: ['./shift-day-detail.component.css']
})
export class ShiftDayDetailComponent implements OnInit {
  @Input() shiftdays: ShiftDays;

  constructor() { }

  ngOnInit(): void {
  }
}
