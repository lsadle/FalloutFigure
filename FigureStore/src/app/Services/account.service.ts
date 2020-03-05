import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {LoginCredentials} from '../ApiModels/login-credentials';
import {NewUserInfo} from '../ApiModels/new-user-info';
import {Observable} from 'rxjs';
import {User} from '../ApiModels/user';

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

    const userId = this.client.post<string>(url, JSON.stringify(credentials), this.httpOptions);
    if (userId == null) {
      throw new Error('Invalid login');
    }

    return userId;
  }

  signup(newUserInfo: NewUserInfo): Observable<User> {
    console.log('Creating new User');
    const url = this.baseUrl + 'CreateUser';

    const user = this.client.post<User>(url, JSON.stringify(newUserInfo), this.httpOptions);
    if (user == null) {
      throw new Error('Problem saving new user');
    }

    return user;
  }
}
