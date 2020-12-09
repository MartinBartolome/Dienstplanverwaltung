import {Component, Input, OnInit} from '@angular/core';
import {Slot} from '../../Models/Slot';

@Component({
  selector: 'app-slot',
  templateUrl: './Slot.component.html',
  styleUrls: ['./Slot.component.css']
})
export class SlotComponent implements OnInit {
  @Input() slot: Slot;

  constructor() { }

  ngOnInit(): void {
  }
}
