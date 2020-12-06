import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Table} from '../../../models/Table';
import {ListItem} from '../../../models/ListItem';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  @Input() table: Table;
  @Output() ItemSelectedEvent = new EventEmitter<ListItem>();
  @Output() AddEvent = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }
  ItemSelected(item: ListItem): void  {
    if (item.selected)
    {
      this.ItemSelectedEvent.emit(item);
    }
  }
  AddNew(): void{
    this.AddEvent.emit();
  }
}
