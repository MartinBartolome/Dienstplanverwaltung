import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Table} from '../models/Table';
import {ListItem} from '../models/ListItem';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  @Input() table: Table;
  @Input() AllowSelection: boolean;
  @Output() EditItemEvent = new EventEmitter<ListItem>();
  @Output() AddEvent = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }
  ItemEdit(item: ListItem): void  {
    this.EditItemEvent.emit(item);
  }
  AddNew(): void{
    this.AddEvent.emit();
  }
  ItemSelected(item: ListItem): void{
    if (!this.AllowSelection)
    {
      item.selected = false;
    }
  }
}
