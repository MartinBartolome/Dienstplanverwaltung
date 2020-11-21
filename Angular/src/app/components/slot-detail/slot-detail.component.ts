import {Component, Input, OnInit} from '@angular/core';
import {Slot} from '../../models/Slot';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';

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
    let url = 'http://192.168.178.20:8080/assignEmployeeToSlot?localId=1&employeeName=' + item + '&slotIdString=' + this.slot.id + 'bar&isAssigned=' + 'false';
    this.sendData(url);
    url = 'http://192.168.178.20:8080/applyEmployeeToSlot?localId=1&employeeName=' + item
      + '&slotIdString=' + this.slot.id + '&isApplied=' + 'true';
    this.sendData(url);
  }
  clickOnApplied(event, item): void{
    let url = 'http://192.168.178.20:8080/assignEmployeeToSlot?localId=1&employeeName=' + item + '&slotIdString=' + this.slot.id + 'bar&isAssigned=' + 'true';
    this.sendData(url);
    url = 'http://192.168.178.20:8080/applyEmployeeToSlot?localId=1&employeeName=' + item
      + '&slotIdString=' + this.slot.id + '&isApplied=' + 'false';
    this.sendData(url);
  }

  private sendData(url): void{
    this.api.get(url).subscribe(data => {
        this.slot = data;
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
