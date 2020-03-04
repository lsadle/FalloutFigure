import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {NewUserInfo} from './ApiModels/new-user-info';
import {Address} from './ApiModels/address';
import {LoginCredentials} from './ApiModels/login-credentials';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };
  baseLoginUrl = 'http://localhost:8080/accounts/';
  loginForm: FormGroup;
  title = 'FigureStore';

  constructor(private builder: FormBuilder, private client: HttpClient) { }

  ngOnInit(): void {
    this.loginForm = this.builder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      street: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required]
    });
  }

  login(): void {
    console.log('login() called');
    const username = this.loginForm.get('username').value;
    const password = this.loginForm.get('password').value;
    const url = this.baseLoginUrl + 'UserLogin';

    const credentials = new LoginCredentials(username, password);

    this.client.post(url, JSON.stringify(credentials), this.httpOptions).subscribe(
      data => console.log(data),
      error => console.log(error));
  }

  signup(): void {
    console.log('signup() called');
    const username = this.loginForm.get('username').value;
    const password = this.loginForm.get('password').value;
    const street = this.loginForm.get('street').value;
    const city = this.loginForm.get('city').value;
    const state = this.loginForm.get('state').value;
    const url = this.baseLoginUrl + 'CreateUser';
    const address = new Address(street, city, state);
    const newUserInfo = new NewUserInfo(username, password, address, null);

    console.log('url: ' + url);
    console.log('data: ' + JSON.stringify(newUserInfo));
    this.client.post(url, JSON.stringify(newUserInfo), this.httpOptions).subscribe(
      data => console.log(data),
      error => console.log(error));
  }
}
