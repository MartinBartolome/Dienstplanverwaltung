import {Table} from '../../General/Models/Table';
import {ShiftTemplateConfigs} from './ShiftTemplateConfigs';

export class ShiftConfiguration {
  shiftTemplatesTable: Table;
  shiftTemplateConfigs: ShiftTemplateConfigs[];
  emptyShiftTemplateConfig: ShiftTemplateConfigs;
}
