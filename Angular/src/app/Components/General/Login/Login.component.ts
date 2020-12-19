import {Component, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {SharedService} from '../../../Common/SharedService';
import {MatDialog} from "@angular/material/dialog";
import {SignUpComponent} from "../SignUp/SignUp.component";
import {UserResponse} from "../Models/UserResponse";
import {DataService} from "../../../Common/DataService";

@Component({
  selector: 'app-login',
  templateUrl: './Login.component.html',
  styleUrls: ['./Login.component.css']
})
export class LoginComponent{
  @Input() error: string | null;
  @Output() submitEM = new EventEmitter();

  form: FormGroup = new FormGroup({
    username: new FormControl('Martin'),
    password: new FormControl(''),
  });

  submit(): void{
    this.api.sendGetRequest('/loginUser?username=' +
      this.form.get('username').value + '&password=' +
      this.form.get('password').value)
      .subscribe((data: UserResponse) => {
        if (data.success)
        {
          this.globalVariables.setNickName(this.form.get('username').value);
          this.globalVariables.setUser(data);
          this.submitEM.emit(true);
        }
        else {
          alert(data.message);
          this.submitEM.emit(false);
        }
      });
  }

  signup(): void{
    const dialogRef = this.dialog.open(SignUpComponent);
    dialogRef.afterClosed().subscribe((result: string)  => {
      if (result)
      {
        this.submitEM.emit(true);
      }
    });
  }

  constructor(public globalVariables: SharedService, public dialog: MatDialog,private api: DataService) { }
}
