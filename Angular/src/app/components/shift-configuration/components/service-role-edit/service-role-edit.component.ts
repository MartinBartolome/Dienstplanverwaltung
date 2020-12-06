import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {SlotInfos} from '../../models/SlotInfos';
import {ListItem} from '../../../general/models/ListItem';

@Component({
  selector: 'app-service-role-edit',
  templateUrl: './service-role-edit.component.html',
  styleUrls: ['./service-role-edit.component.css']
})
export class ServiceRoleEditComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ServiceRoleEditComponent>,
              @Inject(MAT_DIALOG_DATA) public ServiceRole: ListItem) { }

  ngOnInit(): void {
  }

}
