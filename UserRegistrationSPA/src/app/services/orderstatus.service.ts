import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpResponse, HttpHeaders} from '@angular/common/http';
import { User } from '../shared/user.model';
import { OrderStatus } from '../shared/orderstatus.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderStatusService {
  readonly rootUrl = 'http://localhost:5000/';
  constructor(private http: HttpClient) { }

  addOrderStatus(userId, messageId)
  {
    const body = {
       UserId: userId,
       MessageId: messageId
    }
    console.log('addOrderStatus');
    return this.http.post<User>(this.rootUrl + 'api/addOrderStatus', body);
  }

  getOrderStatus(userId)
  {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }

    return this.http.post<User>(this.rootUrl + 'api/getOrderStatus', JSON.stringify(userId), httpOptions);
  }

 
}
