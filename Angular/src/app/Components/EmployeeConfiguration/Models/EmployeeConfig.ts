import {Table} from '../../General/Models/Table';

export class EmployeeConfig {
  nickName: string;
  serviceRoles: Table;
  hourlyRate: number;
  currency: string;
  monthlyContingent: number;
  active: boolean;
}
