import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';

import {  throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import {ShiftTemplateConfigs} from '../components/shift-configuration/models/ShiftTemplateConfigs';
import {stringify} from 'querystring';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private REST_API_SERVER = 'http://192.168.178.20:8080';

  constructor(private httpClient: HttpClient) { }

  handleError(error: HttpErrorResponse): any{
    let errorMessage = 'Unknown error!';
    if (error.error instanceof ErrorEvent) {
      // Client-side errors
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side errors
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }
  public sendGetRequest(specific: string): any {
    return this.httpClient.get(this.REST_API_SERVER + specific).pipe(retry(3), catchError(this.handleError));
  }
  public sendSetRequest(specific: string, data: any): any {
    const header = {   headers: new HttpHeaders({
        'Content-Type':  'application/json',
      }) };
    const body = JSON.stringify(data);
    return this.httpClient.post(this.REST_API_SERVER + specific, body, header).pipe(retry(3),
      catchError(this.handleError));
  }
}
