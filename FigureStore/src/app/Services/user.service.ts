import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {LoginCredentials} from '../ApiModels/login-credentials';
import {User} from '../ApiModels/user';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };
  baseUrl = 'http://localhost:8080/user/';

  constructor(private client: HttpClient, private router: Router) { }

  getUser(userId: string): Observable<User> {
    console.log('Getting user ' + userId);
    const url = this.baseUrl;

    const user = this.client.get<User>(url, this.httpOptions);

    if (user == null) {
      throw new Error('No user with userId ' + userId);
    }

    return user;
  }
}
