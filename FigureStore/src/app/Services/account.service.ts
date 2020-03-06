import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {LoginCredentials} from '../ApiModels/login-credentials';
import {NewUserInfo} from '../ApiModels/new-user-info';
import {Observable, throwError} from 'rxjs';
import {User} from '../ApiModels/user';
import {map, tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };
  baseUrl = 'http://localhost:8080/accounts/';

  constructor(private client: HttpClient) { }

  login(credentials: LoginCredentials): Observable<string> {
    console.log('Logging in');
    const url = this.baseUrl + 'UserLogin';
    const message = 'Invalid Login';

    return this.client.post<string>(url, JSON.stringify(credentials), this.httpOptions).pipe(tap(userId => {
      if (userId == null) {
        throw new Error(message);
      }
    }));

    return throwError(message);
  }

  signup(newUserInfo: NewUserInfo): Observable<User> {
    console.log('Creating new User');
    const url = this.baseUrl + 'CreateUser';
    const message = 'Problem saving new user';

    this.client.post<User>(url, JSON.stringify(newUserInfo), this.httpOptions).pipe(
      tap(user => {
        if (user == null) {
          return throwError(message);
        }
      }));

    return throwError(message);
  }
}
