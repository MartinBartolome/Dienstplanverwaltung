import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {ShiftPlan} from './Models/ShiftPlan';
import {MatDialog} from '@angular/material/dialog';
import {ShiftDayDetailComponent} from './Components/ShiftDayDetail/ShiftDayDetail.component';

@Component({
  selector: 'app-shiftplan',
  templateUrl: './Shiftplan.component.html',
  styleUrls: ['./Shiftplan.component.css']
})
export class ShiftplanComponent implements OnInit, OnChanges {
  @Input() data: ShiftPlan;
  @Output() DateChange = new EventEmitter<Date>();
  @Output() ReloadChange = new EventEmitter<Date>();
  @Input() DateTitle: string;

  constructor(public dialog: MatDialog) {
  }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.DateTitle = this.stringToUSDate(this.data.month).toLocaleString('de-de', {month: 'long', year: 'numeric'});
  }

  ChangeMonth(value: number): void {
    this.data.month = this.DateToString(new Date(
      this.stringToUSDate(this.data.month).setMonth(this.stringToUSDate(this.data.month).getMonth() + value)));
    this.DateChange.emit(this.stringToUSDate(this.data.month));
  }

  clickOpenDetail(SiftDays): void {
    const dialogRef = this.dialog.open(ShiftDayDetailComponent, {
      data: SiftDays
    });
    dialogRef.afterClosed().subscribe(() => {
      this.ReloadChange.emit(this.stringToUSDate(this.data.month));
    });
  }

  DateToString(date: Date): string {
    return date.getDate() + '.' + (date.getMonth() + 1) + '.' + date.getFullYear();
  }

  stringToUSDate(DateString: string): Date {
    return new Date(DateString.split('.', 3)[1].toString() + '/' +
      DateString.split('.', 3)[0].toString() + '/' +
      DateString.split('.', 3)[2].toString());
  }
}
