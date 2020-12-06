import {Table} from '../../general/models/Table';

export class EmployeeConfig {
  nickName: string;
  serviceRoles: Table;
  hourlyRate: number;
  currency: string;
  monthlyContigent: number;
  active: boolean;
}
