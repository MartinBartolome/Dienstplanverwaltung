import {Table} from '../../general/models/Table';

export class EmployeeConfig {
  nickName: string;
  serviceRoles: Table;
  hourlyRate: number;
  currency: string;
  monthlyContingent: number;
  active: boolean;
}
