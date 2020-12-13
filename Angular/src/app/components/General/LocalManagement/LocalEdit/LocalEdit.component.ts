import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ListItem} from '../../Models/ListItem';

@Component({
  selector: 'app-local-edit',
  templateUrl: './LocalEdit.component.html',
  styleUrls: ['./LocalEdit.component.css']
})
export class LocalEditComponent implements OnInit {
  @Output() EditLocal = new EventEmitter();

  constructor(public dialogRef: MatDialogRef<LocalEditComponent>,
              @Inject(MAT_DIALOG_DATA) public Local: ListItem) { }

  ngOnInit(): void {
  }
}
