import {Component, Input, OnInit} from '@angular/core';
import {EmployeeConfig} from '../../models/EmployeeConfig';

@Component({
  selector: 'app-employee-config',
  templateUrl: './employee-config.component.html',
  styleUrls: ['./employee-config.component.css']
})
export class EmployeeConfigComponent implements OnInit {
  @Input() employee: EmployeeConfig;

  constructor() { }

  ngOnInit(): void {
  }

}
