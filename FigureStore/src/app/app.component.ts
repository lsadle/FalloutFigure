import {Component, OnInit} from '@angular/core';
import {AccountService} from './Services/account.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'FigureStore';
  username: string;

  constructor(private accountService: AccountService) { }

  ngOnInit(): void {
    this.accountService.userLoginEvent.subscribe(() => {
      this.username = sessionStorage.getItem('username');
    });
  }

  logout(): void {
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('username');
    this.accountService.userLogoutEvent.emit();
    this.username = null;
  }
}
