import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {ChooselocalComponent} from '../chooselocal/chooselocal.component';
import {DataService} from '../../common/DataService';
import {Table} from '../../models/Table';
import {ListItem} from '../../models/ListItem';
import {SharedService} from '../../common/SharedService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  @Input() error: string | null;
  @Output() submitEM = new EventEmitter();

  form: FormGroup = new FormGroup({
    username: new FormControl('Martin'),
    password: new FormControl(''),
  });

  submit(): void{
    if (this.form.valid) {
      const dialogRef = this.dialog.open(ChooselocalComponent, { data: this.form.get('username').value});
      dialogRef.afterClosed().subscribe(result => {
        this.globalvariables.setNickName(this.form.get('username').value);
        this.submitEM.emit(result.id);
      });
    }
  }

  constructor(public dialog: MatDialog, public globalvariables: SharedService) { }
}
