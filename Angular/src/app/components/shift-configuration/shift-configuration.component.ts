import {Component, Input, OnInit} from '@angular/core';
import {ShiftConfiguration} from './models/ShiftConfiguration';

@Component({
  selector: 'app-shift-configuration',
  templateUrl: './shift-configuration.component.html',
  styleUrls: ['./shift-configuration.component.css']
})
export class ShiftConfigurationComponent implements OnInit {
  @Input() ShiftConfiguration: ShiftConfiguration;
  constructor() { }

  ngOnInit(): void {
  }

  newTemplate(): void{

  }
}
