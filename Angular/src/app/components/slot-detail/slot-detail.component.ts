import {Component, Input, OnInit} from '@angular/core';
import {Slot} from '../../models/Slot';

@Component({
  selector: 'app-slot-detail',
  templateUrl: './slot-detail.component.html',
  styleUrls: ['./slot-detail.component.css']
})
export class SlotDetailComponent implements OnInit {
  @Input() slot: Slot;

  constructor() { }

  ngOnInit(): void {
  }

}
