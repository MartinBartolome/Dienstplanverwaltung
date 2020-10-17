import {Component, Input, OnInit} from '@angular/core';
import {Shift} from '../../models/Shift';

@Component({
  selector: 'app-shift-detail',
  templateUrl: './shift-detail.component.html',
  styleUrls: ['./shift-detail.component.css']
})
export class ShiftDetailComponent implements OnInit {
  @Input()  shift: Shift;
  constructor() { }

  ngOnInit(): void {
  }

}
