import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ListItem} from '../../../General/Models/ListItem';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-service-role-edit',
  templateUrl: './ServiceRoleEdit.component.html',
  styleUrls: ['./ServiceRoleEdit.component.css']
})
export class ServiceRoleEditComponent implements OnInit {

  form: FormGroup = new FormGroup({
    Titel: new FormControl('', [Validators.required])
  });

  constructor(public dialogRef: MatDialogRef<ServiceRoleEditComponent>,
              @Inject(MAT_DIALOG_DATA) public ServiceRole: ListItem) {
  }

  ngOnInit(): void {
    this.form.get('Titel').setValue(this.ServiceRole.title);
  }

  close(): void {
    if (this.form.valid) {
      this.ServiceRole.title = this.form.get('Titel').value;
      this.dialogRef.close(this.ServiceRole);
    }
  }
}
