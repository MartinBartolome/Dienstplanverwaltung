import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {ShiftDays} from './models/ShiftDays';
import {environment} from '../../../environments/environment';
import {ShiftPlan} from './models/ShiftPlan';
import {DataService} from '../../common/DataService';

@Component({
  selector: 'app-shiftplan',
  templateUrl: './shiftplan.component.html',
  styleUrls: ['./shiftplan.component.css']
})
export class ShiftplanComponent implements OnInit {
  public data: ShiftPlan;
  public showDetail = false;
  public selectedDay: ShiftDays;
  private selecteddate: Date;
  public DateTitle: string;

  constructor(private api: DataService) { }

  ngOnInit(): void {
    this.selecteddate = new Date();
    this.LoadData();
  }

  private LoadData(): void{
    this.DateTitle = this.selecteddate.toLocaleString('default', { month: 'long', year: 'numeric'});
    const combinedurl =  '/shiftPlan?month='
      + this.selecteddate.getDate().toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false })
      + '.' + (this.selecteddate.getMonth() + 1).toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false})
      + '.' + this.selecteddate.getFullYear();
    this.api.sendGetRequest(combinedurl).subscribe((data: ShiftPlan) => {
        console.log(data);
        this.data = data;
      });
  }

  NextMonth(): void{
    this.selecteddate.setMonth(this.selecteddate.getMonth() + 1);
    this.LoadData();
  }
  PrevMonth(): void{
    this.selecteddate.setMonth(this.selecteddate.getMonth() - 1);
    this.LoadData();
  }
  clickOpenDetail(Shiftdays): void{
    this.showDetail = !this.showDetail;
    this.selectedDay = Shiftdays;
  }
}
