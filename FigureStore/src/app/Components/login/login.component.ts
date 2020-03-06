import { Component, OnInit } from '@angular/core';
import {LoginCredentials} from '../../ApiModels/login-credentials';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AccountService} from '../../Services/account.service';
import {Router} from '@angular/router';
import {UserService} from '../../Services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  invalidLogin = false;

  constructor(private builder: FormBuilder, private accountService: AccountService, private userService: UserService, private router: Router) { }

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
        this.userService.getUser(data).subscribe(
          (user) => {
            sessionStorage.setItem('userId', user.userId);
            sessionStorage.setItem('username', user.username);
            this.accountService.userLoginEvent.emit();
            console.log('Navigating to Store');
            this.router.navigate(['/store']);
          },
          (err) => console.log(err)
        );
      },
      error => {
        console.log(error);
        this.invalidLogin = true;
      }
    );
  }
}
