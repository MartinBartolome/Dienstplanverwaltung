import {ShiftDays} from './ShiftDays';

export class ShiftPlan {
  id: string;
  type: string;
  date: string;
  comment: string;
  shiftDays: ShiftDays[];
}
