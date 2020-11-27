import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SlotDetailComponent } from './components/shiftplan/components/slot-detail/slot-detail.component';
import { ShiftDetailComponent } from './components/shiftplan/components/shift-detail/shift-detail.component';
import { ShiftDayDetailComponent } from './components/shiftplan/components/shift-day-detail/shift-day-detail.component';
import { TableComponent } from './components/table/table.component';
import { EmployeeConfigComponent } from './components/employee-configuration/components/employee-config/employee-config.component';
import { ShiftplanComponent } from './components/shiftplan/shiftplan.component';
import { HttpClientModule} from '@angular/common/http';
import { ShiftDayComponent } from './components/shiftplan/components/shift-day/shift-day.component';
import { SlotComponent } from './components/shiftplan/components/slot/slot.component';
import { ShiftComponent } from './components/shiftplan/components/shift/shift.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTabsModule} from '@angular/material/tabs';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule} from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { PayrollaccountingComponent } from './components/payrollaccounting/payrollaccounting.component';
import { EmployeeConfigurationComponent } from './components/employee-configuration/employee-configuration.component';
import { EmployeeInviteComponent } from './components/employee-configuration/components/employee-invite/employee-invite.component';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ShiftConfigurationComponent } from './components/shift-configuration/shift-configuration.component';
import { LoginComponent } from './components/login/login.component';
import { ChooselocalComponent } from './components/chooselocal/chooselocal.component';
import { MatOptionModule } from '@angular/material/core';

@NgModule({
  declarations: [
    AppComponent,
    SlotDetailComponent,
    ShiftDetailComponent,
    ShiftDayDetailComponent,
    TableComponent,
    EmployeeConfigComponent,
    ShiftplanComponent,
    ShiftDayComponent,
    SlotComponent,
    ShiftComponent,
    PayrollaccountingComponent,
    EmployeeConfigurationComponent,
    EmployeeInviteComponent,
    ShiftConfigurationComponent,
    LoginComponent,
    ChooselocalComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTabsModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    ReactiveFormsModule,
    FormsModule,
    MatCardModule,
    MatOptionModule,
    MatFormFieldModule,
    MatSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
