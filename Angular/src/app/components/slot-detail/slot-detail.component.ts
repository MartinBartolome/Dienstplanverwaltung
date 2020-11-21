import {Component, Input, OnInit} from '@angular/core';
import {Slot} from '../../models/Slot';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-slot-detail',
  templateUrl: './slot-detail.component.html',
  styleUrls: ['./slot-detail.component.css']
})
export class SlotDetailComponent implements OnInit {
  @Input() slot: any;

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
    this.api.get(environment.Backendserver + url).subscribe((data: any) => {
        this.slot = data.shifts[0].slots[0];
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
