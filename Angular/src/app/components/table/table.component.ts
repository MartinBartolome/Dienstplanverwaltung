import {Component, Input, OnInit} from '@angular/core';
import {Table} from '../../models/Table';
import tableimport from '../../SampleData/Table.json';
import {TableItem} from '../../models/TableItem';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  /*@Input() table: Table;*/
  table: Table = tableimport;
  constructor() { }

  ngOnInit(): void {
  }

  SelectRow(event, item): void{
    item.isSelected = !item.isSelected;
  }
}
