import {Component, Injectable, Input, OnInit} from '@angular/core';
import {EmployeesConfig} from './models/EmployeesConfig';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  @Input() data: EmployeesConfig;

  constructor() {
  }

  ngOnInit(): void {
  }

}
