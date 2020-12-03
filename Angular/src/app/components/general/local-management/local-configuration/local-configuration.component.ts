import { Component, OnInit } from '@angular/core';
import {Table} from '../../../../models/Table';
import {DataService} from '../../../../common/DataService';
import {SharedService} from '../../../../common/SharedService';
import {ShiftEditComponent} from '../../../shift-configuration/components/shift-edit/shift-edit.component';
import {ShiftTemplateConfigs} from '../../../shift-configuration/models/ShiftTemplateConfigs';
import {MatDialog} from '@angular/material/dialog';
import {LocalEditComponent} from '../local-edit/local-edit.component';
import {ListItem} from '../../../../models/ListItem';

@Component({
  selector: 'app-local-configuration',
  templateUrl: './local-configuration.component.html',
  styleUrls: ['./local-configuration.component.css']
})
export class LocalConfigurationComponent implements OnInit {
  Locals: Table;
  constructor(public api: DataService, public globalvariables: SharedService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.GetLocals();
  }
  private GetLocals(): void{
    this.api.sendGetRequest('/getOwnedLocals?userNickName=' + this.globalvariables.getNickName()).subscribe((data: Table) => {
      this.Locals = data;
    });
  }

  public Edit(selected: ListItem): void
  {
    selected.selected = false;
    const dialogRef = this.dialog.open(LocalEditComponent, { data: selected});
    dialogRef.afterClosed().subscribe(result => {
      this.api.sendGetRequest('/updateLocal?localId=' + result.id + '&title=' + result.title + '&isActive=true')
        .subscribe((data: any) => {
          this.Locals = data;
          console.log(data);
        });
    });
  }

  public Add(): void{

    const dialogRef = this.dialog.open(LocalEditComponent, { data: new ListItem()});
    dialogRef.afterClosed().subscribe(result => {
      this.api.sendGetRequest('/requestNewLocal?userNickName=' + this.globalvariables.getNickName() + '&title=' + result.title)
        .subscribe((data: any) => {
          this.Locals = data;
          console.log(data);
        });
    });
  }
}
