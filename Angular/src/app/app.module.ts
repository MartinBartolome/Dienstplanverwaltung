import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SlotDetailComponent } from './components/shiftplan/components/slot-detail/slot-detail.component';
import { ShiftDetailComponent } from './components/shiftplan/components/shift-detail/shift-detail.component';
import { ShiftDayDetailComponent } from './components/shiftplan/components/shift-day-detail/shift-day-detail.component';
import { TableComponent } from './components/general/table/table.component';
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
import { MatIconModule } from '@angular/material/icon';
import { MatDatepickerModule} from '@angular/material/datepicker';
import { MatCheckboxModule} from '@angular/material/checkbox';
import { MatNativeDateModule } from '@angular/material/core';
import { PayrollaccountingComponent } from './components/payrollaccounting/payrollaccounting.component';
import { EmployeeConfigurationComponent } from './components/employee-configuration/employee-configuration.component';
import { EmployeeInviteComponent } from './components/employee-configuration/components/employee-invite/employee-invite.component';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ShiftConfigurationComponent } from './components/shift-configuration/shift-configuration.component';
import { LoginComponent } from './components/general/login/login.component';
import { ChooselocalComponent } from './components/general/local-management/chooselocal/chooselocal.component';
import { MatOptionModule } from '@angular/material/core';
import { ShiftEditComponent } from './components/shift-configuration/components/shift-edit/shift-edit.component';
import { SlotEditComponent } from './components/shift-configuration/components/slot-edit/slot-edit.component';
import { ServiceRoleEditComponent } from './components/shift-configuration/components/service-role-edit/service-role-edit.component';
import { LocalConfigurationComponent } from './components/general/local-management/local-configuration/local-configuration.component';
import { LocalEditComponent } from './components/general/local-management/local-edit/local-edit.component';

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
    ChooselocalComponent,
    ShiftEditComponent,
    SlotEditComponent,
    ServiceRoleEditComponent,
    LocalConfigurationComponent,
    LocalEditComponent
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
    MatSelectModule,
    MatIconModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
