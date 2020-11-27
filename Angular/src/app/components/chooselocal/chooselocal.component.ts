import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {Table} from '../../models/Table';
import {ListItem} from '../../models/ListItem';
import {DataService} from '../../common/DataService';

@Component({
  selector: 'app-chooselocal',
  templateUrl: './chooselocal.component.html',
  styleUrls: ['./chooselocal.component.css']
})
export class ChooselocalComponent implements OnInit {
  Locals: Table;
  selected: ListItem;
  constructor(public dialogRef: MatDialogRef<ChooselocalComponent>,
              @Inject(MAT_DIALOG_DATA) public Name: string,
              public api: DataService){}

  ngOnInit(): void {
    this.GetLocals();
  }

  private GetLocals(): void{
    this.api.sendGetRequest('/getChooseLocal?userNickName=' + this.Name).subscribe((data: Table) => {
      this.Locals = data;
    });
  }
}
