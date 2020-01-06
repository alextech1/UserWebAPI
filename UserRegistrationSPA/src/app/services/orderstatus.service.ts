import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import { User } from '../shared/user.model';
import { OrderStatus } from '../shared/orderstatus.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderStatusService {
  readonly rootUrl = "http://localhost:5000/" //listen to API
  constructor(private http: HttpClient) { }

  addOrderStatus(userId, messageId) 
  {
    const body = {
       UserId: userId,
       MessageId: messageId
    }
    console.log("here");
    return this.http.post<User>(this.rootUrl + 'api/addOrderStatus', body);
  }

  getOrderStatus(userId) 
  {
    return this.http.post<User>(this.rootUrl + 'api/getOrderStatus', userId);
  }

 
}
