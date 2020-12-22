import {Component, OnInit} from '@angular/core';
import {SysAdminTenantConfig} from '../../Models/SysAdminTenantConfig';
import {DataService} from '../../../../Common/DataService';
import {ListItem} from "../../Models/ListItem";
import {EmployeeConfig} from "../../../EmployeeConfiguration/Models/EmployeeConfig";

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

  Stilllegen(): void {
    for (const o of this.SysadminData.table1Data.table1.items) {
      if (o.selected) {
        this.api.sendGetRequest('/localSetState?localId=' + o.id
          + '&isActive=false').subscribe((data: SysAdminTenantConfig) => {
          this.SysadminData = data;
        });
      }
    }
  }

  Bewilligen(): void {
    for (const o of this.SysadminData.table2Data.table1.items) {
      if (o.selected) {
        this.api.sendGetRequest('/grantLocal?localId=' + o.id).subscribe((data: SysAdminTenantConfig) => {
          this.SysadminData = data;
        });
      }
    }
  }

  Aktivieren(): void {
    for (const o of this.SysadminData.table3Data.table1.items) {
      if (o.selected) {
        this.api.sendGetRequest('/localSetState?localId=' + o.id
          + '&isActive=true').subscribe((data: SysAdminTenantConfig) => {
          this.SysadminData = data;
        });
      }
    }
  }
}
