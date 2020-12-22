import {Table} from '../../General/Models/Table';
import {RecOptions} from './RecOptions';
import {SlotInfo} from './SlotInfo';

export class ShiftTemplateConfigs {
  recurrenceOptions: RecOptions;
  days: Table;
  slots: Table;
  fromDate: string;
  toDate: string;
  title: string;
  id: number;
  slotInfos: SlotInfo[];
  startTime: string;
  endTime: string;
}
