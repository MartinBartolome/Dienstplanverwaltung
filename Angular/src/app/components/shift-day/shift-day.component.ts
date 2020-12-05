import {Component, Input, OnInit} from '@angular/core';
import {ShiftDays} from '../../models/ShiftDays';

@Component({
  selector: 'app-shift-day',
  templateUrl: './shift-day.component.html',
  styleUrls: ['./shift-day.component.css']
})
export class ShiftDayComponent implements OnInit {
  @Input() shiftdays: ShiftDays;

  constructor() { }

  ngOnInit(): void {
  }

}
