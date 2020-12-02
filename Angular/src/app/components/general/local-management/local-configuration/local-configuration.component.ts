import { Component, OnInit } from '@angular/core';
import {Table} from '../../../../models/Table';
import {DataService} from '../../../../common/DataService';
import {SharedService} from '../../../../common/SharedService';

@Component({
  selector: 'app-local-configuration',
  templateUrl: './local-configuration.component.html',
  styleUrls: ['./local-configuration.component.css']
})
export class LocalConfigurationComponent implements OnInit {
  Locals: Table;
  constructor(public api: DataService, public globalvariables: SharedService) { }

  ngOnInit(): void {
    this.GetLocals();
  }
  private GetLocals(): void{
    this.api.sendGetRequest('/getOwnedLocals?userNickName=' + this.globalvariables.getNickName()).subscribe((data: Table) => {
      this.Locals = data;
    });
  }
}
