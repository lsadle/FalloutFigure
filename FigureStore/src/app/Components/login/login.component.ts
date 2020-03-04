import { Component, OnInit } from '@angular/core';
import {LoginCredentials} from '../../ApiModels/login-credentials';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {AccountService} from '../../Services/account.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  invalidLogin = false;

  constructor(private builder: FormBuilder, private accountService: AccountService) { }

  ngOnInit(): void {
    this.loginForm = this.builder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login(): void {
    const username = this.loginForm.get('username').value;
    const password = this.loginForm.get('password').value;
    const credentials = new LoginCredentials(username, password);

    try {
      this.accountService.login(credentials);
    } catch (e) {
      this.invalidLogin = true;
    }
  }
}
