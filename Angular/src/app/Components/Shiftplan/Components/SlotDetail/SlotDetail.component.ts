import {Component, Input, OnInit} from '@angular/core';
import {Slot} from '../../Models/Slot';
import {DataService} from '../../../../Common/DataService';
import {SharedService} from '../../../../Common/SharedService';
import {ShiftDays} from '../../Models/ShiftDays';

@Component({
  selector: 'app-slot-detail',
  templateUrl: './SlotDetail.component.html',
  styleUrls: ['./SlotDetail.component.css']
})
export class SlotDetailComponent implements OnInit {
  @Input() slot: Slot;

  constructor(private api: DataService, public globalVariables: SharedService) { }

  ngOnInit(): void {
  }

  Apply(value): void
  {
    const url = '/applyEmployeeToSlot?localId=' + this.globalVariables.getLocalID() + '&employeeName=' +
      this.globalVariables.getNickName() + '&slotIdString=' + this.slot.id + '&isApplied=' + value;
    this.sendData(url);
    if (value === false)
    {
      if (this.slot.assigned.indexOf(this.globalVariables.getNickName()) >= 0)
      {
        this.clickOnAssigned(this.globalVariables.getNickName());
      }
    }
  }

  clickOnAssigned(item): void{
    if (this.slot.allowedUserInteraction.includes('Assign'))
    {
      const url = '/assignEmployeeToSlot?localId=1&employeeName=' + item + '&slotIdString=' + this.slot.id + '&isAssigned=' + 'false';
      this.sendData(url);
    }
  }
  clickOnApplied(item): void{
    if (this.slot.allowedUserInteraction.includes('Assign')) {
      const url = '/assignEmployeeToSlot?localId=1&employeeName=' + item + '&slotIdString=' + this.slot.id + '&isAssigned=' + 'true';
      this.sendData(url);
    }
  }

  private sendData(url): void{
    this.api.sendGetRequest(url).subscribe((data: Slot) => {
        this.slot = data;
      });
  }
}
