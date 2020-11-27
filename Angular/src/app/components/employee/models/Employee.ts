import {Table} from '../../../models/Table';

export class Employee {
  employeeEmail: string;
  type: string;
  serviceRolesTable: Table;
  isActive: boolean;
  hourlyRate: string;
  currency: string;
  monthlyContingent: string;
}
