import {Component, OnInit} from '@angular/core';
import {MatTabsModule} from '@angular/material/tabs';
import {EmployeesConfig} from './components/employee/models/EmployeesConfig';
import {DataService} from './common/DataService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  title = 'Dienstplanverwaltung';
  EmployeeData: EmployeesConfig;

  constructor(private api: DataService) {
    this.loadEmployeeData();
  }

  private loadEmployeeData(): void{
    this.api.sendGetRequest('/getEmployeesConfig?localId=1').subscribe((data: any) => {
      this.EmployeeData = data;
      console.log(data);
    });
  }
}
