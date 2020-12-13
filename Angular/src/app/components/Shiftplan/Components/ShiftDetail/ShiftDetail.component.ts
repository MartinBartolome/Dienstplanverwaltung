import {Component, Input, OnInit} from '@angular/core';
import {Shift} from '../../Models/Shift';

@Component({
  selector: 'app-shift-detail',
  templateUrl: './ShiftDetail.component.html',
  styleUrls: ['./ShiftDetail.component.css']
})
export class ShiftDetailComponent implements OnInit {
  @Input()  shift: Shift;
  constructor() { }

  ngOnInit(): void {
  }
}
