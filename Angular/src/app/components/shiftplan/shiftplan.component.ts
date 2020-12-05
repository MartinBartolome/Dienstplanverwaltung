import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {ShiftDays} from '../../models/ShiftDays';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-shiftplan',
  templateUrl: './shiftplan.component.html',
  styleUrls: ['./shiftplan.component.css']
})
export class ShiftplanComponent implements OnInit {
  url = environment.Backendserver + '/shiftPlan';
  public data: any;
  public showDetail = false;
  public selectedDay: ShiftDays;
  private selecteddate: Date;
  public DateTitle: string;

  constructor(private api: HttpClient) { }

  ngOnInit(): void {
    this.selecteddate = new Date();
    this.LoadData();
  }

  private LoadData(): void{
    this.DateTitle = this.selecteddate.toLocaleString('default', { month: 'long', year: 'numeric'});
    const combinedurl =  this.url + '?month='
      + this.selecteddate.getDate().toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false })
      + '.' + (this.selecteddate.getMonth() + 1).toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false})
      + '.' + this.selecteddate.getFullYear();
    this.api.get(combinedurl).subscribe(data => {
        this.data = data;
        console.log(data);
      },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log('Client-side error occured.');
        } else {
          console.log('Server-side error occured.');
        }
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
