import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {Table} from '../../Models/Table';
import {ListItem} from '../../Models/ListItem';
import {DataService} from '../../../../Common/DataService';
import {SharedService} from '../../../../Common/SharedService';
import {LocalConfigurationComponent} from '../LocalConfiguration/LocalConfiguration.component';
import {TenantConfigComponent} from '../../SysAdmin/TenantConfig/TenantConfig.component';

@Component({
  selector: 'app-chooselocal',
  templateUrl: './ChooseLocal.component.html',
  styleUrls: ['./ChooseLocal.component.css']
})
export class ChooselocalComponent implements OnInit {
  Locals: Table;
  selected: ListItem;
  @Output() submitEM = new EventEmitter();

  constructor(public api: DataService, public globalVariables: SharedService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.GetLocals();
  }

  public SelectLocal(): void {
    this.globalVariables.setLocalID(this.selected.id);
    this.globalVariables.setLocalName(this.selected.title);
    this.submitEM.emit(true);
  }

  private GetLocals(): void {
    this.api.sendGetRequest('/getChooseLocal?userNickName=' + this.globalVariables.getNickName()).subscribe((data: Table) => {
      this.Locals = data;
    });
  }

  public ManageLocals(): void {
    const dialogRef = this.dialog.open(LocalConfigurationComponent, {});
    dialogRef.afterClosed().subscribe(() => {
      this.GetLocals();
    });
  }
}
