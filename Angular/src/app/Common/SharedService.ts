import {Injectable} from '@angular/core';
import {UserResponse} from '../Components/General/Models/UserResponse';

@Injectable({
  providedIn: 'root',
})

export class SharedService{
  private LocalID: number;
  private Localname: string;
  private Nickname: string;
  private IsManager: boolean;
  private User: UserResponse;

  setLocalID(setID: number): void{
    this.LocalID = setID;
  }

  getLocalID(): number{
    return this.LocalID;
  }
  setLocalName(localname: string): void{
    this.Localname = localname;
  }
  getLocalName(): string{
    return this.Localname;
  }

  setNickName(setNick: string): void{
    this.Nickname = setNick;
  }
  getNickName(): string{
    return this.Nickname;
  }
  setUser(setNick: UserResponse): void{
    this.User = setNick;
  }
  getUser(): UserResponse{
    return this.User;
  }
  setisManager(ismanager: boolean): void{
    this.IsManager = ismanager;
  }
  getisManager(): boolean{
    return this.IsManager;
  }
}
