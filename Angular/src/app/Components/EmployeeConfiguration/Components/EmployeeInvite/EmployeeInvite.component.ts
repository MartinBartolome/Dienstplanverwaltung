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
  Cancel(): void {
    this.dialogRef.close(this.email);
  }
}

