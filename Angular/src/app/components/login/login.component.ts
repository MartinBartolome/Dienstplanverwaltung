import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  @Input() error: string | null;
  @Output() submitEM = new EventEmitter();

  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  submit(): void{
    if (this.form.valid) {
      this.submitEM.emit(true);
    }
  }
  constructor() { }
}
