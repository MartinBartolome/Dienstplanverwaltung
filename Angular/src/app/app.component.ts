import {Component} from '@angular/core';
import {EmployeesConfig} from './Components/EmployeeConfiguration/Models/EmployeesConfig';
import {DataService} from './Common/DataService';
import {ShiftPlan} from './Components/ShiftPlan/Models/ShiftPlan';
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
    this.api.sendGetRequest('/getEmployeesConfig?localId=' + this.globalVariables.getLocalID()).subscribe((data: any) => {
      this.EmployeeData = data;
      console.log(data);
    });
  }
  public loadShiftPlanData(SelectedDate: Date): void{
    const CombinedURL =  '/shiftPlan?month='
      + SelectedDate.getDate().toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false })
      + '.' + (SelectedDate.getMonth() + 1).toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false})
      + '.' + SelectedDate.getFullYear();
    this.api.sendGetRequest(CombinedURL).subscribe((data: ShiftPlan) => {
      console.log(data);
      this.ShiftPlanData = data;
    });
  }

  public loadShiftConfiguration(): void{
    this.api.sendGetRequest('/getShiftPlanConfig?localId=' + this.globalVariables.getLocalID()).subscribe((data: any) => {
      this.ShiftConfiguration = data;
      console.log(data);
    });
  }

  public Reload(): void
  {
    this.loadEmployeeData();
    this.loadShiftPlanData(new Date());
    this.loadShiftConfiguration();
  }
}
