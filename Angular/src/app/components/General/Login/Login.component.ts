import {Component, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {SharedService} from '../../../Common/SharedService';

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
    if (this.form.valid) {
      this.globalVariables.setNickName(this.form.get('username').value);
      this.submitEM.emit(true);
    }
  }

  constructor(public globalVariables: SharedService) { }
}
