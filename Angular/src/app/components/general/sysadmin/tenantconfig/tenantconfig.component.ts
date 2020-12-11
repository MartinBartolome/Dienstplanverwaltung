import { Component, OnInit } from '@angular/core';
import {SysAdminTenantConfig} from '../../Models/SysAdminTenantConfig';
import {DataService} from '../../../../Common/DataService';

@Component({
  selector: 'app-tenantconfig',
  templateUrl: './TenantConfig.component.html',
  styleUrls: ['./TenantConfig.component.css']
})
export class TenantConfigComponent implements OnInit {
  SysadminData: SysAdminTenantConfig;

  constructor(private api: DataService) {
    this.api.sendGetRequest('/getSysAdminTenantConfig').subscribe((data: SysAdminTenantConfig) => {
      this.SysadminData = data;
    });
  }

  ngOnInit(): void {
  }

}
