import {Component, Input, OnInit} from '@angular/core';
import {ShiftDays} from '../../Models/ShiftDays';

@Component({
  selector: 'app-shift-day',
  templateUrl: './ShiftDay.component.html',
  styleUrls: ['./ShiftDay.component.css']
})
export class ShiftDayComponent implements OnInit {
  @Input() ShiftDays: ShiftDays;

  constructor() {
  }

  ngOnInit(): void {
  }

}
