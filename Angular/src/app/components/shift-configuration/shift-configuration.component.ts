import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ShiftConfiguration} from './models/ShiftConfiguration';
import {ListItem} from '../../models/ListItem';
import {EmployeeInviteComponent} from '../employee-configuration/components/employee-invite/employee-invite.component';
import {MatDialog} from '@angular/material/dialog';
import {ShiftEditComponent} from './components/shift-edit/shift-edit.component';
import {ChooselocalComponent} from '../general/local-management/chooselocal/chooselocal.component';
import {ShiftTemplateConfigs} from './models/ShiftTemplateConfigs';
import {DataService} from '../../common/DataService';
import {SharedService} from '../../common/SharedService';

@Component({
  selector: 'app-shift-configuration',
  templateUrl: './shift-configuration.component.html',
  styleUrls: ['./shift-configuration.component.css']
})
export class ShiftConfigurationComponent implements OnInit {
  @Input() ShiftConfiguration: ShiftConfiguration;
  @Output() ShiftConfigChanged = new EventEmitter();

  constructor(public dialog: MatDialog, private api: DataService, public globalvariables: SharedService) { }

  ngOnInit(): void {
  }

  newTemplate(): void{
    const dialogRef = this.dialog.open(ShiftEditComponent, { data: this.ShiftConfiguration.emptyShiftTemplateConfig });
    dialogRef.afterClosed().subscribe(result => {
      alert('Now do the Edit!');
    });
  }

  public editTemplate(template: ListItem): void{
    template.selected = false;
    const dialogRef = this.dialog.open(ShiftEditComponent,
      { data: this.ShiftConfiguration.shiftTemplateConfigs[
          this.ShiftConfiguration.shiftTemplatesTable.items.indexOf(template)]});
    dialogRef.afterClosed().subscribe((result: ShiftTemplateConfigs)  => {
      alert(result.fromDate);
      if (result)
      {
        this.api.sendSetRequest('/updateShiftTemplateConfig', result).subscribe((data: ShiftTemplateConfigs) => {
          this.ShiftConfiguration.shiftTemplateConfigs[
            this.ShiftConfiguration.shiftTemplatesTable.items.indexOf(template)] = data;
          console.log(data);
          this.ShiftConfigChanged.emit(true);
        });
      }
    });
  }
}
