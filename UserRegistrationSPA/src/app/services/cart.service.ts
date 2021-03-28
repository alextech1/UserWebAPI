import { Injectable } from '@angular/core';
import { map, flatMap } from 'rxjs/operators';
import { Cart } from '../entities/cart.entity';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CartRes } from '../res/cart.res';
import { MsgRes } from '../res/msg.res';

@Injectable({
    providedIn: 'root'
  })
export class CartService {
    private carts: Cart[];
    private baseUrl = environment.apiUrl;
    public cartModel: Cart;

    constructor(private http: HttpClient) {
        this.carts = [];
    }

    findAll(searchStr: string): Observable<CartRes>{
        console.log('findAll cart')
        const httpOptions = {
            headers: new HttpHeaders({
              'Content-Type':  'application/json',
              // 'Authorization': 'jwt-token'
            })
          };
        return this.http.post<CartRes>(this.baseUrl + 'getCarts', {searchStr: ''}, httpOptions)
            .pipe();
    }

    findCartByUserId(body): Observable<CartRes>{
      console.log('findCartByUserId');
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        })
      };

      return this.http.post<CartRes>(this.baseUrl + 'findUserCart', body, httpOptions).pipe();
    }

    addCart(body): Observable<MsgRes> {
        console.log('addCart');
        const httpOptions = {
            headers: new HttpHeaders({
              'Content-Type':  'application/json',
              // 'Authorization': 'jwt-token'
            })
          };
          console.log('body', body);
        return this.http.post<MsgRes>(this.baseUrl + 'addCart', body, httpOptions).pipe();
    }

    editCart(body): Observable<MsgRes> {
        const httpOptions = {
            headers: new HttpHeaders({
              'Content-Type':  'application/json',
              // 'Authorization': 'jwt-token'
            })
          };
        return this.http.post<MsgRes>(this.baseUrl + 'editCart', body, httpOptions).pipe();
    }

    deleteCart(body):  Observable<CartRes>{
        const httpOptions = {
            headers: new HttpHeaders({
              'Content-Type':  'application/json',
              // 'Authorization': 'jwt-token'
            })
          };
        return this.http.post<CartRes>(this.baseUrl + 'deleteCart', body, httpOptions).pipe();
    }
}