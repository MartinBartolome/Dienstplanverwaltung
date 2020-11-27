import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SlotDetailComponent } from './components/shiftplan/components/slot-detail/slot-detail.component';
import { ShiftDetailComponent } from './components/shiftplan/components/shift-detail/shift-detail.component';
import { ShiftDayDetailComponent } from './components/shiftplan/components/shift-day-detail/shift-day-detail.component';
import { TableComponent } from './components/table/table.component';
import { EmployeeConfigComponent } from './components/employee/components/employee-config/employee-config.component';
import { EmployeesConfigComponent } from './components/employee/components/employees-config/employees-config.component';
import { ShiftplanComponent } from './components/shiftplan/shiftplan.component';
import {HttpClientModule} from '@angular/common/http';
import { ShiftDayComponent } from './components/shiftplan/components/shift-day/shift-day.component';
import { SlotComponent } from './components/shiftplan/components/slot/slot.component';
import { ShiftComponent } from './components/shiftplan/components/shift/shift.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTabsModule} from '@angular/material/tabs';
import { EmployeeComponent } from './components/employee/employee.component';
import { PayrollaccountingComponent } from './components/payrollaccounting/payrollaccounting.component';

@NgModule({
  declarations: [
    AppComponent,
    SlotDetailComponent,
    ShiftDetailComponent,
    ShiftDayDetailComponent,
    TableComponent,
    EmployeeConfigComponent,
    EmployeesConfigComponent,
    ShiftplanComponent,
    ShiftDayComponent,
    SlotComponent,
    ShiftComponent,
    EmployeeComponent,
    PayrollaccountingComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTabsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
