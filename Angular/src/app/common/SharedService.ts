import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root',
})

export class SharedService{
  private LocalID: number;

  setLocalID(setID: number): void{
    this.LocalID = setID;
  }

  getLocalID(): number{
    return this.LocalID;
  }
}
