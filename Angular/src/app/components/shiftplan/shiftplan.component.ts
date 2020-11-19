import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-shiftplan',
  templateUrl: './shiftplan.component.html',
  styleUrls: ['./shiftplan.component.css']
})
export class ShiftplanComponent implements OnInit {
  url = 'http://192.168.178.20:8080/shiftPlan';
  public data: any;

  constructor(private api: HttpClient) { }

  ngOnInit(): void {
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
}
