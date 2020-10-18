import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SlotDetailComponent } from './components/slot-detail/slot-detail.component';
import { ShiftDetailComponent } from './components/shift-detail/shift-detail.component';
import { ShiftDayDetailComponent } from './components/shift-day-detail/shift-day-detail.component';
import { TableComponent } from './components/table/table.component';
import { EmployeeConfigComponent } from './components/employee-config/employee-config.component';
import { EmployeesConfigComponent } from './components/employees-config/employees-config.component';

@NgModule({
  declarations: [
    AppComponent,
    SlotDetailComponent,
    ShiftDetailComponent,
    ShiftDayDetailComponent,
    TableComponent,
    EmployeeConfigComponent,
    EmployeesConfigComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
