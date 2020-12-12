import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ListItem} from '../../../General/Models/ListItem';

@Component({
  selector: 'app-service-role-edit',
  templateUrl: './ServiceRoleEdit.component.html',
  styleUrls: ['./ServiceRoleEdit.component.css']
})
export class ServiceRoleEditComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ServiceRoleEditComponent>,
              @Inject(MAT_DIALOG_DATA) public ServiceRole: ListItem) { }

  ngOnInit(): void {
  }

}
