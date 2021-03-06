import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { CartService } from '../services/cart.service';
import { Product } from '../shared/product.model';
import { ProductService } from '../services/product.service';


@Component({
  selector: 'app-add-cart',
  templateUrl: './add-cart.component.html',
  styleUrls: ['./add-cart.component.css']
})
export class AddCartComponent implements OnInit {
  private userId: string;
  private cartId: string;
  private productId: number;
  private quantity: number;
  private bodyData: any;
  private addForm: FormGroup;
  private products: Product[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private cartService: CartService,
    private productService: ProductService,
    private router: Router
    ) { }

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      product: ['',  Validators.required],
      quantity: ['', Validators.required]
    });
    this.productService.findAll('').subscribe(resp => {
      if (resp) {
        this.products = resp.productList;
      }
    })
  }

  onSubmit() {
    console.log(this.addForm);
    this.quantity = this.addForm.value.quantity;
    this.productId = parseInt(this.addForm.value.product, 10);
    this.userId = localStorage.getItem('id');
    this.cartId = localStorage.getItem('cartId');
    this.bodyData = {
      cartId: this.cartId,
      userId: this.userId,
      productId: this.productId,
      quantity: this.quantity
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
