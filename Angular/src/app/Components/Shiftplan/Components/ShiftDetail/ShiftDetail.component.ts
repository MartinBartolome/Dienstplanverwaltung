import {Component, Input, OnInit} from '@angular/core';
import {Shift} from '../../Models/Shift';
import {DataService} from '../../../../Common/DataService';
import {SharedService} from '../../../../Common/SharedService';

@Component({
  selector: 'app-shift-detail',
  templateUrl: './ShiftDetail.component.html',
  styleUrls: ['./ShiftDetail.component.css']
})
export class ShiftDetailComponent implements OnInit {
  @Input()  shift: Shift;
  constructor(private api: DataService, public globalVariables: SharedService) { }

  ngOnInit(): void {
  }

  toggleCancel(): void{
    this.api.sendGetRequest('/shiftSetIsCanceled?shiftId=' + this.shift.id + '&localId='
      + this.globalVariables.getLocalID() + '&employeeName='
      + this.globalVariables.getNickName() + '&isCanceled=' + !this.shift.canceled).subscribe((data: Shift) => {
      this.shift = data;
    });
  }
}
