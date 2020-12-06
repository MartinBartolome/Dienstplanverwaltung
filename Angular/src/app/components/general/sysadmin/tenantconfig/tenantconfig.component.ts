import { Component, OnInit } from '@angular/core';
import {SysAdminTenantConfig} from '../../models/SysAdminTenantConfig';
import {DataService} from '../../../../common/DataService';
import {SharedService} from '../../../../common/SharedService';

@Component({
  selector: 'app-tenantconfig',
  templateUrl: './tenantconfig.component.html',
  styleUrls: ['./tenantconfig.component.css']
})
export class TenantconfigComponent implements OnInit {
  SysadminData: SysAdminTenantConfig;

  constructor(private api: DataService, public globalvariables: SharedService) {
    this.api.sendGetRequest('/getSysAdminTenantConfig').subscribe((data: SysAdminTenantConfig) => {
      this.SysadminData = data;
      console.log(data);
    });
  }

  ngOnInit(): void {
  }

}
