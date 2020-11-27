import {Table} from '../../../models/Table';
import {Employee} from './Employee';
import {ListItem} from '../../../models/ListItem';

export class Employees {
  type: string;
  employeesTable: Table;
  employeesConfigs: Employee[];
}
