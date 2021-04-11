import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { CartService } from '../services/cart.service';
import { Product } from '../shared/product.model';
import { ProductService } from '../services/product.service';
import { Observable, Subscription } from 'rxjs';
import { map } from 'rxjs/operators';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit, OnDestroy {
  state$: Subscription;
  private userId: string;
  private cartId: string;
  private productId: number;
  private quantity: number;
  private bodyData: any;
  private addForm: FormGroup;
  private products: Product[] = [];

  constructor(private formBuilder: FormBuilder,
    private cartService: CartService,
    private productService: ProductService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    let searchParam = '';

    this.state$ = this.activatedRoute.queryParams.subscribe(params => {
      searchParam = params["searchStr"];

      this.productService.findAll(searchParam).subscribe(resp => {
        if (resp) {
          this.products = resp.productList;
        } else {
          console.log('error finding product');
        }
      })
    })
  }

  ngOnDestroy() {
    this.state$.unsubscribe();
  }

  addToCart(prodId: any): void {
    //console.log(this.addForm);
    //this.quantity = this.addForm.value.quantity;
    this.productId = parseInt(prodId, 10);
    this.userId = localStorage.getItem('id');
    this.cartId = localStorage.getItem('cartId');
    this.bodyData = {
      cartId: this.cartId,
      userId: this.userId,
      productId: this.productId,
      quantity: 1
    };
    console.log(this.bodyData);
    this.cartService.addCart(this.bodyData).subscribe(resp => {
      if (resp) {
        const message = resp.message;
        this.router.navigate(['cart']);
      }
    });
  }

}
