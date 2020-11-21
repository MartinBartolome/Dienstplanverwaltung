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

  constructor(private api: HttpClient) { }

  ngOnInit(): void {
    const today = new Date();
    this.url += '?month=' + today.getDate().toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false })
      + '.' + (today.getMonth() + 1).toLocaleString('de-de', {minimumIntegerDigits: 2, useGrouping: false}) + '.' + today.getFullYear();
    this.api.get(this.url).subscribe(data => {
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

  clickOpenDetail(Shiftdays): void{
    this.showDetail = !this.showDetail;
    this.selectedDay = Shiftdays;
  }
}
