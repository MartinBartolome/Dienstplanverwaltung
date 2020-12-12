import {Table} from '../../General/Models/Table';
import {RecOptions} from './RecOptions';
import {SlotInfos} from './SlotInfos';

export class ShiftTemplateConfigs {
    recurrenceOptions: RecOptions;
    days: Table;
    slots: Table;
    fromDate: string;
    toDate: string;
    title: string;
    id: number;
    slotInfos: SlotInfos[];
    startTime: string;
    endTime: string;
}
