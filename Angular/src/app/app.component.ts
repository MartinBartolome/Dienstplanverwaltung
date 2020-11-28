import {Component, OnInit} from '@angular/core';
import {MatTabsModule} from '@angular/material/tabs';
import {EmployeesConfig} from './components/employee-configuration/models/EmployeesConfig';
import {DataService} from './common/DataService';
import {ShiftPlan} from './components/shiftplan/models/ShiftPlan';
import {ShiftTemplateConfigs} from './components/shift-configuration/models/ShiftTemplateConfigs';
import {ShiftConfiguration} from './components/shift-configuration/models/ShiftConfiguration';
import {SharedService} from './common/SharedService';
import {Local} from 'protractor/built/driverProviders';

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
  loginsuccess = false;

  constructor(private api: DataService, public globalvariables: SharedService) {
  }

  public loadEmployeeData(): void{
    this.api.sendGetRequest('/getEmployeesConfig?localId=' + this.globalvariables.getLocalID()).subscribe((data: any) => {
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
    this.api.sendGetRequest('/getShiftPlanConfig?localId=' + this.globalvariables.getLocalID()).subscribe((data: any) => {
      this.ShiftConfiguration = data;
      console.log(data);
    });
  }

  public Login(LocalID: number): void
  {
    this.loginsuccess = true;
    this.globalvariables.setLocalID(LocalID);
    this.loadEmployeeData();
    this.loadShiftPlanData(new Date());
    this.loadShiftConfiguration();
  }
}
