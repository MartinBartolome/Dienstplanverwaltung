import {Component, Input, OnInit} from '@angular/core';
import {Shift} from '../../Models/Shift';

@Component({
  selector: 'app-shift',
  templateUrl: './Shift.component.html',
  styleUrls: ['./Shift.component.css']
})
export class ShiftComponent implements OnInit {
  @Input() shift: Shift;

  constructor() {
  }

  ngOnInit(): void {
  }

}
