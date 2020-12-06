import {Table} from '../../../models/Table';

export class EmployeeConfig {
  nickName: string;
  serviceRoles: Table;
  hourlyRate: number;
  currency: string;
  monthlyContigent: number;
  active: boolean;
}
