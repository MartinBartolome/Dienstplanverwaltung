import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root',
})

export class SharedService{
  private LocalID: number;
  private Nickname: string;

  setLocalID(setID: number): void{
    this.LocalID = setID;
  }

  getLocalID(): number{
    return this.LocalID;
  }

  setNickName(setNick: string): void{
    this.Nickname = setNick;
  }
  getNickName(): string{
    return this.Nickname;
  }
}
