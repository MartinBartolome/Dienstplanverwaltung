import {Component, OnInit} from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup, FormGroupDirective, NgForm,
  ValidationErrors,
  ValidatorFn,
  Validators
} from '@angular/forms';
import {DataService} from '../../../Common/DataService';
import {SharedService} from '../../../Common/SharedService';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {UserResponse} from '../Models/UserResponse';
import {ErrorStateMatcher} from "@angular/material/core";

@Component({
  selector: 'app-sign-up',
  templateUrl: './SignUp.component.html',
  styleUrls: ['./SignUp.component.css']
})
export class SignUpComponent implements OnInit {
  form: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [ Validators.required ]),
    confirmpassword: new FormControl('', [Validators.required])
  }, { validators: passwordMatchValidator});
  hide = true;
  rehide = true;
  constructor(private api: DataService, public globalVariables: SharedService, public dialog: MatDialog,
              public dialogRef: MatDialogRef<SignUpComponent>) { }

  ngOnInit(): void {
  }

  checkregistration(): void{
    if (this.form.valid){
      this.api.sendGetRequest('/registerUser?username=' +
        this.form.get('username').value + '&password=' +
        this.form.get('password').value)
        .subscribe((data: UserResponse) => {
          alert(data.message);
          if (data.success)
          {
            this.globalVariables.setNickName(this.form.get('username').value);
            this.globalVariables.setUser(data);
            this.dialogRef.close(true);
          }
          else {
            this.dialogRef.close(false);
          }
        });
    }
  }

  get password(): AbstractControl { return this.form.get('password'); }
  get confirmpassword(): AbstractControl { return this.form.get('confirmpassword'); }

  onPasswordInput(): void {
    if (this.form.hasError('passwordMismatch')) {
      this.confirmpassword.setErrors([{passwordMismatch: true}]);
    }
    else {
      this.confirmpassword.setErrors(null);
    }
  }
}
export const passwordMatchValidator: ValidatorFn = (formGroup: FormGroup): ValidationErrors | null => {
  if (formGroup.get('password').value === formGroup.get('confirmpassword').value) {
    return null;
  }
  else {
    return { passwordMismatch: true};
  }
};

