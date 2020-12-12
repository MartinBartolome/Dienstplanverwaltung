import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ShiftConfiguration} from './Models/ShiftConfiguration';
import {ListItem} from '../General/Models/ListItem';
import {MatDialog} from '@angular/material/dialog';
import {ShiftEditComponent} from './Components/ShiftEdit/ShiftEdit.component';
import {ShiftTemplateConfigs} from './Models/ShiftTemplateConfigs';
import {DataService} from '../../Common/DataService';
import {SharedService} from '../../Common/SharedService';

@Component({
  selector: 'app-shift-configuration',
  templateUrl: './ShiftConfiguration.component.html',
  styleUrls: ['./ShiftConfiguration.component.css']
})
export class ShiftConfigurationComponent implements OnInit {
  @Input() ShiftConfiguration: ShiftConfiguration;
  @Output() ShiftConfigChanged = new EventEmitter();

  constructor(public dialog: MatDialog, private api: DataService, public globalVariables: SharedService) { }

  ngOnInit(): void {
  }

  newTemplate(): void{
    const dialogRef = this.dialog.open(ShiftEditComponent, { data: this.ShiftConfiguration.emptyShiftTemplateConfig });
    dialogRef.afterClosed().subscribe((result: ShiftTemplateConfigs) => {
      if (result)
      {
        this.api.sendPostRequest('/updateShiftTemplateConfig?localId=' + this.globalVariables.getLocalID(), result)
          .subscribe((data: ShiftTemplateConfigs) => {
            this.ShiftConfiguration.shiftTemplateConfigs.push(data);
            this.ShiftConfigChanged.emit(true);
          });
      }
    });
  }

  public editTemplate(template: ListItem): void{
    const dialogRef = this.dialog.open(ShiftEditComponent,
      { data: this.ShiftConfiguration.shiftTemplateConfigs[
          this.ShiftConfiguration.shiftTemplatesTable.items.indexOf(template)]});
    dialogRef.afterClosed().subscribe((result: ShiftTemplateConfigs)  => {
      if (result)
      {
        alert(result.fromDate);
        this.api.sendPostRequest('/updateShiftTemplateConfig?localId=' + this.globalVariables.getLocalID(), result)
          .subscribe((data: ShiftTemplateConfigs) => {
          this.ShiftConfiguration.shiftTemplateConfigs[
            this.ShiftConfiguration.shiftTemplatesTable.items.indexOf(template)] = data;
          this.ShiftConfigChanged.emit(true);
        });
      }
    });
  }
}
