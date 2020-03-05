import { Component, OnInit } from '@angular/core';
import {LoginCredentials} from '../../ApiModels/login-credentials';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {AccountService} from '../../Services/account.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  invalidLogin = false;

  constructor(private builder: FormBuilder, private accountService: AccountService, private router: Router) { }

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

    this.accountService.login(credentials).subscribe(
      data => {
        console.log('Navigating to Store');
        this.router.navigate(['/store'], {queryParams: {userId: data}});
      },
      error => {
        console.log(error);
        this.invalidLogin = true;
      }
    );
  }
}
