import { Component, OnInit } from '@angular/core';
import {Table} from '../../Models/Table';
import {DataService} from '../../../../Common/DataService';
import {SharedService} from '../../../../Common/SharedService';
import {MatDialog} from '@angular/material/dialog';
import {LocalEditComponent} from '../LocalEdit/LocalEdit.component';
import {ListItem} from '../../Models/ListItem';

@Component({
  selector: 'app-local-configuration',
  templateUrl: './LocalConfiguration.component.html',
  styleUrls: ['./LocalConfiguration.component.css']
})
export class LocalConfigurationComponent implements OnInit {
  Locals: Table;
  constructor(public api: DataService, public globalVariables: SharedService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.GetLocals();
  }
  private GetLocals(): void{
    this.api.sendGetRequest('/getOwnedLocals?userNickName=' + this.globalVariables.getNickName()).subscribe((data: Table) => {
      this.Locals = data;
    });
  }

  public Edit(selected: ListItem): void
  {
    selected.selected = false;
    const dialogRef = this.dialog.open(LocalEditComponent, { data: selected});
    dialogRef.afterClosed().subscribe((result: ListItem) => {
      this.api.sendGetRequest('/updateLocal?localId=' + result.id + '&title=' + result.title + '&isActive=true')
        .subscribe((data: Table) => {
          this.Locals = data;
        });
    });
  }

  public Add(): void{

    const dialogRef = this.dialog.open(LocalEditComponent, { data: new ListItem()});
    dialogRef.afterClosed().subscribe((result: ListItem) => {
      this.api.sendGetRequest('/requestNewLocal?userNickName=' + this.globalVariables.getNickName() + '&title=' + result.title)
        .subscribe((data: Table) => {
          this.Locals = data;
        });
    });
  }
}
