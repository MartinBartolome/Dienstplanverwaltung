import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {ShiftDays} from './models/ShiftDays';
import {ShiftPlan} from './models/ShiftPlan';
import {MatDialog} from '@angular/material/dialog';
import {ShiftDayDetailComponent} from './components/shift-day-detail/shift-day-detail.component';

@Component({
  selector: 'app-shiftplan',
  templateUrl: './shiftplan.component.html',
  styleUrls: ['./shiftplan.component.css']
})
export class ShiftplanComponent implements OnInit, OnChanges {
  @Input() data: ShiftPlan;
  @Output() DateChange = new EventEmitter<Date>();
  @Input() DateTitle: string;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void
  {
    this.DateTitle = this.stringToUSDate(this.data.month).toLocaleString('de-de', { month: 'long', year: 'numeric'});
  }

  ChangeMonth(value: number): void
  {
    this.data.month = this.DateToString(new Date(
      this.stringToUSDate(this.data.month).setMonth(this.stringToUSDate(this.data.month).getMonth() + value)));
    this.DateChange.emit(this.stringToUSDate(this.data.month));
  }

  clickOpenDetail(Shiftdays): void{
    const dialogRef = this.dialog.open(ShiftDayDetailComponent, {
      data: Shiftdays
    });
  }
  DateToString(date: Date): string
  {
    return date.getDate() + '.' + (date.getMonth() + 1) + '.' + date.getFullYear();
  }
  stringToUSDate(datestring: string): Date
  {
    return new Date(datestring.split('.', 3)[1].toString() + '/' +
      datestring.split('.', 3)[0].toString() + '/' +
      datestring.split('.', 3)[2].toString());
  }
}
