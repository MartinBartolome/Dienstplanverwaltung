import {ShiftDays} from './ShiftDays';

export class ShiftPlan {
  id: number;
  type: string;
  date: string;
  comment: string;
  shiftDays: ShiftDays[];
}
