import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Table} from '../Models/Table';
import {ListItem} from '../Models/ListItem';

@Component({
  selector: 'app-table',
  templateUrl: './Table.component.html',
  styleUrls: ['./Table.component.css']
})
export class TableComponent implements OnInit {
  @Input() table: Table;
  @Input() AllowSelection: boolean;
  @Input() AllowDelete: boolean;
  @Output() EditItemEvent = new EventEmitter<ListItem>();
  @Output() DeleteItemEvent = new EventEmitter<ListItem>();
  @Output() ItemSelectedEvent = new EventEmitter<ListItem>();
  @Output() AddEvent = new EventEmitter();

  constructor() {
  }

  ngOnInit(): void {
  }

  ItemEdit(item: ListItem): void {
    this.EditItemEvent.emit(item);
  }

  ItemDelete(item: ListItem): void {
    this.DeleteItemEvent.emit(item);
  }

  AddNew(): void {
    this.AddEvent.emit();
  }

  ItemSelected(item: ListItem): void {
    if (!this.AllowSelection) {
      item.selected = false;
      this.ItemSelectedEvent.emit(item);
    }
  }
}
