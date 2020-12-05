import {Table} from '../../../models/Table';
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
}
