import {Component, Input, OnInit} from '@angular/core';
import {EmployeesConfig} from './models/EmployeesConfig';

@Component({
  selector: 'app-employee-configuration',
  templateUrl: './employee-configuration.component.html',
  styleUrls: ['./employee-configuration.component.css']
})
export class EmployeeConfigurationComponent implements OnInit {
  @Input() data: EmployeesConfig;

  constructor() { }

  ngOnInit(): void {
  }

}
