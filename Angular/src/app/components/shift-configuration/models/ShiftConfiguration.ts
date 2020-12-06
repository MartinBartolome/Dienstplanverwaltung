import {Table} from '../../general/models/Table';
import {ShiftTemplateConfigs} from './ShiftTemplateConfigs';

export class ShiftConfiguration {
  shiftTemplatesTable: Table;
  shiftTemplateConfigs: ShiftTemplateConfigs[];
  emptyShiftTemplateConfig: ShiftTemplateConfigs;
}
