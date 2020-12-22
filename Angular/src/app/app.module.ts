import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {SlotDetailComponent} from './Components/Shiftplan/Components/SlotDetail/SlotDetail.component';
import {ShiftDetailComponent} from './Components/Shiftplan/Components/ShiftDetail/ShiftDetail.component';
import {ShiftDayDetailComponent} from './Components/Shiftplan/Components/ShiftDayDetail/ShiftDayDetail.component';
import {TableComponent} from './Components/General/Table/Table.component';
import {EmployeeConfigComponent} from './Components/EmployeeConfiguration/Components/EmployeeConfig/EmployeeConfig.component';
import {ShiftplanComponent} from './Components/Shiftplan/Shiftplan.component';
import {HttpClientModule} from '@angular/common/http';
import {ShiftDayComponent} from './Components/Shiftplan/Components/ShiftDay/ShiftDay.component';
import {SlotComponent} from './Components/Shiftplan/Components/Slot/Slot.component';
import {ShiftComponent} from './Components/Shiftplan/Components/Shift/Shift.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatTabsModule} from '@angular/material/tabs';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MAT_DATE_LOCALE, MatNativeDateModule} from '@angular/material/core';
import {EmployeeConfigurationComponent} from './Components/EmployeeConfiguration/EmployeeConfiguration.component';
import {EmployeeInviteComponent} from './Components/EmployeeConfiguration/Components/EmployeeInvite/EmployeeInvite.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ShiftConfigurationComponent} from './Components/ShiftConfiguration/ShiftConfiguration.component';
import {LoginComponent} from './Components/General/Login/Login.component';
import {ChooselocalComponent} from './Components/General/LocalManagement/ChooseLocal/ChooseLocal.component';
import {MatOptionModule} from '@angular/material/core';
import {ShiftEditComponent} from './Components/ShiftConfiguration/Components/ShiftEdit/ShiftEdit.component';
import {SlotEditComponent} from './Components/ShiftConfiguration/Components/SlotEdit/SlotEdit.component';
import {ServiceRoleEditComponent} from './Components/ShiftConfiguration/Components/ServiceRoleEdit/ServiceRoleEdit.component';
import {LocalConfigurationComponent} from './Components/General/LocalManagement/LocalConfiguration/LocalConfiguration.component';
import {LocalEditComponent} from './Components/General/LocalManagement/LocalEdit/LocalEdit.component';
import {TenantConfigComponent} from './Components/General/SysAdmin/TenantConfig/TenantConfig.component';
import {SignUpComponent} from './Components/General/SignUp/SignUp.component';
import {DoubleTableComponent} from './Components/General/DoubleTable/DoubleTable.component';
import {MatMenuModule} from '@angular/material/menu';

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
    EmployeeConfigurationComponent,
    EmployeeInviteComponent,
    ShiftConfigurationComponent,
    LoginComponent,
    ChooselocalComponent,
    ShiftEditComponent,
    SlotEditComponent,
    ServiceRoleEditComponent,
    LocalConfigurationComponent,
    LocalEditComponent,
    TenantConfigComponent,
    SignUpComponent,
    DoubleTableComponent,
    DoubleTableComponent
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
    MatNativeDateModule,
    MatMenuModule
  ],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'de-de'},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
