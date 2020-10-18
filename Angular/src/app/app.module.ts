import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SlotDetailComponent } from './components/slot-detail/slot-detail.component';
import { ShiftDetailComponent } from './components/shift-detail/shift-detail.component';
import { ShiftDayDetailComponent } from './components/shift-day-detail/shift-day-detail.component';
import { TableComponent } from './components/table/table.component';

@NgModule({
  declarations: [
    AppComponent,
    SlotDetailComponent,
    ShiftDetailComponent,
    ShiftDayDetailComponent,
    TableComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
