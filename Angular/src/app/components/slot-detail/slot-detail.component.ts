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

  clickOnAssigned(event, item): void{
    this.slot.assigned = this.slot.assigned.filter(obj => obj !== item);
    this.slot.applied.push(item);
  }
  clickOnApplied(event, item): void{
    this.slot.applied = this.slot.applied.filter(obj => obj !== item);
    this.slot.assigned.push(item);
  }
}
