import {Component, Input, OnInit} from '@angular/core';
import {Table} from '../../models/Table';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  @Input() table: Table;

  constructor() { }

  ngOnInit(): void {
  }

  SelectRow(event, item): void{
    item.isSelected = !item.isSelected;
  }
}
