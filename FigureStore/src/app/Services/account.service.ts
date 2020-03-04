import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {LoginCredentials} from '../ApiModels/login-credentials';
import {NewUserInfo} from '../ApiModels/new-user-info';
import {Router} from '@angular/router';

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

  constructor(private client: HttpClient, private router: Router) { }

  login(credentials: LoginCredentials): void {
    console.log('Logging in');
    const url = this.baseUrl + 'UserLogin';

    this.client.post<string>(url, JSON.stringify(credentials), this.httpOptions).subscribe(
      data => {
        if (data != null) {
          console.log('Navigating to Store');
          this.router.navigate(['/store'], {queryParams: {userId: data}});
        } else {
          throw new Error();
        }
      },
      error => console.log(error));
  }

  signup(newUserInfo: NewUserInfo): void {
    console.log('Creating new User');
    const url = this.baseUrl + 'CreateUser';

    this.client.post(url, JSON.stringify(newUserInfo), this.httpOptions).subscribe(
      data => {
        console.log(data);
        if (data != null) {
          console.log('Navigating to Login');
          this.router.navigate(['/login']);
        } else {
          throw new Error();
        }
      },
      error => console.log(error));
  }
}
