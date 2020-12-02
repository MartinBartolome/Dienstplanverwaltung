import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {Table} from '../../models/Table';
import {ListItem} from '../../models/ListItem';
import {DataService} from '../../common/DataService';
import {SharedService} from '../../common/SharedService';

@Component({
  selector: 'app-chooselocal',
  templateUrl: './chooselocal.component.html',
  styleUrls: ['./chooselocal.component.css']
})
export class ChooselocalComponent implements OnInit {
  Locals: Table;
  selected: ListItem;
  @Output() submitEM = new EventEmitter();

  constructor(public api: DataService, public globalvariables: SharedService){}

  ngOnInit(): void {
    this.GetLocals();
  }

  private SelectLocal(): void{
    this.globalvariables.setLocalID(this.selected.id);
    this.submitEM.emit(true);
  }

  private GetLocals(): void{
    this.api.sendGetRequest('/getChooseLocal?userNickName=' + this.globalvariables.getNickName()).subscribe((data: Table) => {
      this.Locals = data;
    });
  }
}
