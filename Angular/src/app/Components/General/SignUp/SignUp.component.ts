import {Component, Inject, OnInit} from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {Table} from '../Models/Table';
import {DataService} from '../../../Common/DataService';
import {SharedService} from '../../../Common/SharedService';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-sign-up',
  templateUrl: './SignUp.component.html',
  styleUrls: ['./SignUp.component.css']
})
export class SignUpComponent implements OnInit {
  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(private api: DataService, public globalVariables: SharedService, public dialog: MatDialog,
              public dialogRef: MatDialogRef<SignUpComponent>) { }

  ngOnInit(): void {
  }

  checkregistration(): void{
    this.api.sendGetRequest('/register?username=' +
      this.form.get('username').value + '&password=' +
      this.form.get('username').value)
      .subscribe((data: boolean) => {
        if (data)
        {
          this.globalVariables.setNickName(this.form.get('username').value);
          this.dialogRef.close(true);
        }
        else {
          this.dialogRef.close(false);
        }
      });
  }

}
