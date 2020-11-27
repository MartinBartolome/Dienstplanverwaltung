import {Component, Input, OnInit} from '@angular/core';
import {Table} from '../../models/Table';
import {ListItem} from '../../models/ListItem';

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
}
