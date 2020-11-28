import {Component, OnInit} from '@angular/core';
import {MatTabsModule} from '@angular/material/tabs';
import {EmployeesConfig} from './components/employee-configuration/models/EmployeesConfig';
import {DataService} from './common/DataService';
import {ShiftPlan} from './components/shiftplan/models/ShiftPlan';
import {ShiftTemplateConfigs} from './components/shift-configuration/models/ShiftTemplateConfigs';
import {ShiftConfiguration} from './components/shift-configuration/models/ShiftConfiguration';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  title = 'Dienstplanverwaltung';
  EmployeeData: EmployeesConfig;
  ShiftConfiguration: ShiftConfiguration;
  ShiftPlanData: ShiftPlan;
  LocalID: number;
  loginsuccess = false;

  constructor(private api: DataService) {
  }

  public loadEmployeeData(): void{
    this.api.sendGetRequest('/getEmployeesConfig?localId=' + this.LocalID).subscribe((data: any) => {
      this.EmployeeData = data;
      console.log(data);
    });
  }
  public loadShiftPlanData(selecteddate: Date): void{
    const combinedurl =  '/shiftPlan?month='
      + selecteddate.getDate().toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false })
      + '.' + (selecteddate.getMonth() + 1).toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false})
      + '.' + selecteddate.getFullYear();
    this.api.sendGetRequest(combinedurl).subscribe((data: ShiftPlan) => {
      console.log(data);
      this.ShiftPlanData = data;
    });
  }

  public loadShiftConfiguration(): void{
    this.api.sendGetRequest('/getShiftPlanConfig?localId=' + this.LocalID).subscribe((data: any) => {
      this.ShiftConfiguration = data;
      console.log(data);
    });
  }

  public Login(LocalID: number): void
  {
    this.loginsuccess = true;
    this.LocalID = LocalID;
    this.loadEmployeeData();
    this.loadShiftPlanData(new Date());
    this.loadShiftConfiguration();
  }
}
