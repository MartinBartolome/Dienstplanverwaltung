import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';


@Component({
  selector: 'app-employee-invite',
  templateUrl: './employee-invite.component.html',
  styleUrls: ['./employee-invite.component.css']
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
