import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Figure} from '../ApiModels/figure';
import {User} from '../ApiModels/user';
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FigureService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };
  baseUrl = 'http://localhost:8080/figure/';

  constructor(private client: HttpClient) { }

  get(id: string): Observable<Figure> {
    console.log('Getting Figure ' + id);
    const url = this.baseUrl + id;

    return this.client.get<Figure>(url, this.httpOptions);
  }

  getAll(): Observable<Figure[]> {
    console.log('Getting all Figures');
    const url = this.baseUrl;

    return this.client.get<Figure[]>(url, this.httpOptions);
  }
}
