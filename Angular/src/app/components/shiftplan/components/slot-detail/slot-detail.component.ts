import {Component, EventEmitter, Input, OnInit, Output, SimpleChanges} from '@angular/core';
import {Slot} from '../../models/Slot';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {environment} from '../../../../../environments/environment';
import {GlobalConstants} from '../../../../common/GlobalConstants';

@Component({
  selector: 'app-slot-detail',
  templateUrl: './slot-detail.component.html',
  styleUrls: ['./slot-detail.component.css']
})
export class SlotDetailComponent implements OnInit {
  @Input() slot: Slot;

  constructor(private api: HttpClient) { }

  ngOnInit(): void {
  }

  clickOnAssigned(event, item): void{
    let url = '/assignEmployeeToSlot?localId=1&employeeName=' + item + '&slotIdString=' + this.slot.id + '&isAssigned=' + 'false';
    this.sendData(url);
    url = '/applyEmployeeToSlot?localId=1&employeeName=' + item
      + '&slotIdString=' + this.slot.id + '&isApplied=' + 'true';
    this.sendData(url);
  }
  clickOnApplied(event, item): void{
    let url = '/assignEmployeeToSlot?localId=1&employeeName=' + item + '&slotIdString=' + this.slot.id + '&isAssigned=' + 'true';
    this.sendData(url);
    url = '/applyEmployeeToSlot?localId=1&employeeName=' + item
      + '&slotIdString=' + this.slot.id + '&isApplied=' + 'false';
    this.sendData(url);
  }

  private sendData(url): void{
    this.api.get(GlobalConstants.Backendserver + url).subscribe((data: any) => {
        this.slot.assigned = data.shifts[0].slots[0].assigned;
        this.slot.applied = data.shifts[0].slots[0].applied;
        this.slot.title = data.shifts[0].slots[0].title;
        console.log(data);
      },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log('Client-side error occured.');
        } else {
          console.log('Server-side error occured.');
        }
      });
  }
}
