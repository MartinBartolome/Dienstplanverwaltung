import {Component, Input, OnInit} from '@angular/core';
import {ShiftConfiguration} from './models/ShiftConfiguration';
import {ListItem} from '../../models/ListItem';
import {EmployeeInviteComponent} from '../employee-configuration/components/employee-invite/employee-invite.component';
import {MatDialog} from '@angular/material/dialog';
import {ShiftEditComponent} from './components/shift-edit/shift-edit.component';
import {ChooselocalComponent} from '../chooselocal/chooselocal.component';
import {ShiftTemplateConfigs} from './models/ShiftTemplateConfigs';

@Component({
  selector: 'app-shift-configuration',
  templateUrl: './shift-configuration.component.html',
  styleUrls: ['./shift-configuration.component.css']
})
export class ShiftConfigurationComponent implements OnInit {
  @Input() ShiftConfiguration: ShiftConfiguration;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  newTemplate(): void{
    const dialogRef = this.dialog.open(ShiftEditComponent, { data: new ShiftTemplateConfigs()});
    dialogRef.afterClosed().subscribe(result => {
      alert('Now do the Edit!');
    });
  }

  public editTemplate(template: ListItem): void{
    template.selected = false;
    const dialogRef = this.dialog.open(ShiftEditComponent,
      { data: this.ShiftConfiguration.shiftTemplateConfigs[this.ShiftConfiguration.shiftTemplatesTable.items.indexOf(template)]});
    dialogRef.afterClosed().subscribe(result => {
      alert('Now do the Edit!');
    });
  }
}
