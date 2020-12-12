import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';


@Component({
  selector: 'app-employee-invite',
  templateUrl: './EmployeeInvite.component.html',
  styleUrls: ['./EmployeeInvite.component.css']
})
export class EmployeeInviteComponent implements OnInit {
  email: string;
  constructor(public dialogRef: MatDialogRef<EmployeeInviteComponent>){ }

  ngOnInit(): void {
  }

  sendMail(): void{
    alert('Mail to:' + this.email);
    this.dialogRef.close();
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
}
