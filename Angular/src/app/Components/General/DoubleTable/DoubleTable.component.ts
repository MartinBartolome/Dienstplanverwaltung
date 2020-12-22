import {Component, Input, OnInit} from '@angular/core';
import {ListItem} from '../Models/ListItem';
import {SysAdminTableData} from '../Models/SysAdminTableData';

@Component({
  selector: 'app-doubletable',
  templateUrl: './DoubleTable.component.html',
  styleUrls: ['./DoubleTable.component.css']
})
export class DoubleTableComponent implements OnInit {
  @Input() tables: SysAdminTableData;
  @Input() AllowSelection: boolean;

  constructor() {
  }

  ngOnInit(): void {
  }

  ItemSelectedt1(item: ListItem): void {
    if (!this.AllowSelection) {

    } else {
      this.tables.table2.items[this.tables.table1.items.indexOf(item)].selected = item.selected;
    }
  }

  ItemSelectedt2(item: ListItem): void {
    if (!this.AllowSelection) {
      item.selected = false;
    } else {
      this.tables.table1.items[this.tables.table2.items.indexOf(item)].selected = item.selected;
    }
  }
}
