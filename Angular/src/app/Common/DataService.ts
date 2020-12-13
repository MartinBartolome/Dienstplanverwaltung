import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';

import {  throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DataService {
<<<<<<< Updated upstream:Angular/src/app/common/DataService.ts

<<<<<<< HEAD:Angular/src/app/Common/DataService.ts


=======
<<<<<<< Updated upstream:Angular/src/app/common/DataService.ts
=======
>>>>>>> Stashed changes:Angular/src/app/Common/DataService.ts
=======
>>>>>>> Stashed changes:Angular/src/app/Common/DataService.ts
>>>>>>> SpringFeatures:Angular/src/app/common/DataService.ts
  private REST_API_SERVER = 'http://localhost:8080';


  constructor(private httpClient: HttpClient) { }

  handleError(error: HttpErrorResponse): any{
    let errorMessage;
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
  public sendPostRequest(specific: string, data: any): any {
    const header = {   headers: new HttpHeaders({
        'Content-Type':  'application/json',
      }) };
    const body = JSON.stringify(data);
    return this.httpClient.post(this.REST_API_SERVER + specific, body, header).pipe(retry(3),
      catchError(this.handleError));
  }
}
