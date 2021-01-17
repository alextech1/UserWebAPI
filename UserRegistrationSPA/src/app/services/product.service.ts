import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, flatMap } from 'rxjs/operators';
import { Product } from '../entities/product.entity';
import { environment } from 'src/environments/environment';
import {catchError, retry} from 'rxjs/internal/operators';
import {ProductRes} from '../res/product.res';


@Injectable({
    providedIn: 'root'
  })
export class ProductService {
    private products: Product[];
    private baseUrl = environment.apiUrl;
    public productModel: Product;

    constructor(private http: HttpClient) {
        this.products = [];
    }

    findAll(searchStr: string): Observable<ProductRes>{
        console.log('findall product');
        const httpOptions = {
            headers: new HttpHeaders({
              'Content-Type':  'application/json',
              // 'Authorization': 'jwt-token'
            })
          };
        return this.http.post<ProductRes>(this.baseUrl + 'getProducts', {searchStr: ''}, httpOptions)
            .pipe();
    }

    find(id: string): Product {
        return this.products[this.getSelectedIndex(id)];
    }

    private getSelectedIndex(id: string) {
        for (let i = 0; i < this.products.length; i++) {
            if (this.products[i].id == parseInt(id)) {
                return i;
            }
        }
        return -1;
    }
}