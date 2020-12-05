import {Component, EventEmitter, Input, OnInit, Output, SimpleChanges} from '@angular/core';
import {Slot} from '../../models/Slot';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {DataService} from '../../../../common/DataService';
import {SharedService} from '../../../../common/SharedService';

@Component({
  selector: 'app-slot-detail',
  templateUrl: './slot-detail.component.html',
  styleUrls: ['./slot-detail.component.css']
})
export class SlotDetailComponent implements OnInit {
  @Input() slot: Slot;

  constructor(private api: DataService, public globalvariables: SharedService) { }

  ngOnInit(): void {
  }

  Apply(value): void
  {
    const url = '/applyEmployeeToSlot?localId=' + this.globalvariables.getLocalID() + '&employeeName=' +
      this.globalvariables.getNickName() + '&slotIdString=' + this.slot.id + '&isApplied=' + value;
    this.sendData(url);
    if (value === false)
    {
      if (this.slot.assigned.indexOf(this.globalvariables.getNickName()) >= 0)
      {
        this.clickOnAssigned(this.globalvariables.getNickName());
      }
    }
  }

  clickOnAssigned(item): void{
    const url = '/assignEmployeeToSlot?localId=1&employeeName=' + item + '&slotIdString=' + this.slot.id + '&isAssigned=' + 'false';
    this.sendData(url);
  }
  clickOnApplied(item): void{
    const url = '/assignEmployeeToSlot?localId=1&employeeName=' + item + '&slotIdString=' + this.slot.id + '&isAssigned=' + 'true';
    this.sendData(url);
  }

  private sendData(url): void{
    this.api.sendGetRequest(url).subscribe((data: any) => {
        this.slot.assigned = data.shifts[0].slots[0].assigned;
        this.slot.applied = data.shifts[0].slots[0].applied;
        this.slot.title = data.shifts[0].slots[0].title;
        console.log(data);
      });
  }
}
