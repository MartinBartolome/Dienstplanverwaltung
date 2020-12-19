import {Component} from '@angular/core';
import {EmployeesConfig} from './Components/EmployeeConfiguration/Models/EmployeesConfig';
import {DataService} from './Common/DataService';
import {ShiftPlan} from './Components/Shiftplan/Models/ShiftPlan';
import {ShiftConfiguration} from './Components/ShiftConfiguration/Models/ShiftConfiguration';
import {SharedService} from './Common/SharedService';

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
  LoginSuccess = false;
  LocalSelected = false;

  constructor(private api: DataService, public globalVariables: SharedService) {
  }

  public loadEmployeeData(): void{
    this.api.sendGetRequest('/getEmployeesConfig?localId=' + this.globalVariables.getLocalID()
      + '&employeeName=' + this.globalVariables.getNickName()).subscribe((data: EmployeesConfig) => {
      this.EmployeeData = data;
    });
  }
  public loadShiftPlanData(SelectedDate: Date): void{
    const CombinedURL =  '/shiftPlan?localId=' + this.globalVariables.getLocalID() + '&employeeName='
      + this.globalVariables.getNickName() + '&month='
      + SelectedDate.getDate().toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false })
      + '.' + (SelectedDate.getMonth() + 1).toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false})
      + '.' + SelectedDate.getFullYear();
    this.api.sendGetRequest(CombinedURL).subscribe((data: ShiftPlan) => {
      this.ShiftPlanData = data;
    });
  }

  public loadShiftConfiguration(): void{
    this.api.sendGetRequest('/getShiftPlanConfig?localId=' + this.globalVariables.getLocalID()
      + '&employeeName=' + this.globalVariables.getNickName()).subscribe((data: ShiftConfiguration) => {
      this.ShiftConfiguration = data;
    });
  }

  public Reload(): void
  {
    this.loadEmployeeData();
    this.loadShiftPlanData(new Date());
    this.loadShiftConfiguration();
  }
}
