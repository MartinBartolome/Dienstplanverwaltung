import { Component, OnInit } from '@angular/core';
import {ShiftPlan} from '../../models/ShiftPlan';
import shiftplanimport from '../../SampleData/ShiftPlan.json';

@Component({
  selector: 'app-shiftplan',
  templateUrl: './shiftplan.component.html',
  styleUrls: ['./shiftplan.component.css']
})
export class ShiftplanComponent implements OnInit {
  CurrentMonth: number;
  CurrentYear: number;
  TableDays: Array<number>;
  ShiftPlan: ShiftPlan = shiftplanimport;

  /*
  austauschen, wenn es die Daten von wo anders kommen
  @Input() ShiftPlan: ShiftPlan;
*/

  constructor() { }

  ngOnInit(): void {
    const date = new Date();
    const y = date.getFullYear();
    const m = date.getMonth();
    this.CurrentMonth = m.valueOf();
    this.CurrentYear = y.valueOf();

    this.setDate();
  }

  setDate(): void
  {
    const date = new Date();
    this.TableDays = new Array<number>();
    date.setFullYear(this.CurrentYear, this.CurrentMonth);
    const firstDay = new Date(this.CurrentYear, this.CurrentMonth - 1, 1);
    const lastDay = new Date(this.CurrentYear, this.CurrentMonth, 0);
    let firstdayset: boolean;
    let counter: number;
    counter = 1;
    firstdayset = false;
    for (let i = 0; i < 42; i++) {
      if (firstdayset) {
        if (lastDay.getDate() + firstDay.getDay() - 1 < i) {
          this.TableDays.push(0);
        } else {
          this.TableDays.push(counter++);
        }

      } else {
        if (firstDay.getDay() === i) {
          this.TableDays.push(counter++);
          firstdayset = true;
        } else {
          this.TableDays.push(0);
        }
      }
    }
  }

  clickNextMonth(): void{
    this.CurrentMonth++;
    if (this.CurrentMonth > 11)
    {
      this.CurrentMonth = 0;
      this.CurrentYear++;
    }
    this.setDate();
  }
  clickPrevMonth(): void{
    this.CurrentMonth--;
    if (this.CurrentMonth < 0)
    {
      this.CurrentMonth = 11;
    }

    this.setDate();
  }
}
