import {Slot} from './Slot';

export class Shift {
  id: string;
  type: string;
  title: string;
  from: string;
  to: string;
  isCanceled: boolean;
  slots: Slot[];
}
