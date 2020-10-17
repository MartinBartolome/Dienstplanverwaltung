import {Assigned} from './Assigned';
import {Applied} from './Applied';

export class Slot {
  id: string;
  type: string;
  title: string;
  assigned: Assigned[];
  applied: Applied[];
}
