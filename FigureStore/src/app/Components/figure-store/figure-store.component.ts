import {Component, OnInit} from '@angular/core';
import {UserService} from '../../Services/user.service';
import {FigureService} from '../../Services/figure.service';
import {Figure} from '../../ApiModels/figure';
import {User} from '../../ApiModels/user';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-figure-store',
  templateUrl: './figure-store.component.html',
  styleUrls: ['./figure-store.component.css']
})
export class FigureStoreComponent implements OnInit {
  user: User;
  figures: Figure[];

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private figureService: FigureService) {
  }

  ngOnInit(): void {
    const userId = sessionStorage.getItem('userId');
    this.userService.getUser(userId).subscribe(user => this.user = user, err => console.log(err));

    this.figureService.getAll().subscribe(figures => this.figures = figures, err => console.log(err));
  }

  onPurchase(figureId: string) {

  }

  onDetail(figureId: string) {

  }
}
