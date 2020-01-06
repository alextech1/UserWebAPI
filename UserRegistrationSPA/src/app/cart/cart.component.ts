import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Item } from '../entities/item.entity';
import {  CartService } from '../services/cart.service';
import { Router } from '@angular/router';
import { CartModel } from '../shared/cart.model';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  private items: Item[] = [];
  private total: number = 0;
  private userId: string;
  private carts: CartModel[] = [];
  private cartModel: CartModel;
  private bodyData: any;
  private headElements = ['Option','Id', 'Name', 'Photo', 'Price', 'Quantity', 'Sub Total'];
  constructor(
    private activatedRoute: ActivatedRoute,
    private cartService: CartService,
    private router: Router
  ) { }

  ngOnInit() {
     this.loadCart();
  }

  loadCart(): void {
    this.total = 0;
    this.cartService.findAll('').subscribe(resp => {
        console.log('resp', resp);
        if (resp) {
          if (resp.message === 'success') {
              const dataList = resp.cartList;
              for (var i = 0; i < dataList.length; i++) {
                  var item = dataList[i];
                  this.cartModel = new CartModel();
                  this.cartModel.Id = item.id;
                  this.cartModel.ProductId = item.product.id;
                  this.cartModel.Name = item.product.name;
                  this.cartModel.Quantity = item.quantity;
                  this.cartModel.Price = item.product.price;
                  this.cartModel.Photo = item.product.photo;
                  this.carts.push(this.cartModel);
                  this.total += item.product.price * item.quantity;
              }
          } else {
              console.log('Cart failed');
          }
        } else {
          console.log('Cart failed');
        }
      });
  }

  update(id: number, quantity: number): void {
    localStorage.removeItem('cartId');
    localStorage.removeItem('quanity');
    localStorage.setItem('cartId', id.toString());
    localStorage.setItem('quantity', quantity.toString());
    this.router.navigate(['edit-cart']);
  }

  add(): void {
    this.router.navigate(['add-cart']);
  }

  remove(id: number): void {
    this.bodyData = {
      cartId: id,
      userId: 1, 
      productId: 1,
      quantity: 1
    };
    this.cartService.deleteCart(this.bodyData).subscribe(resp => {
      console.log('resp', resp);
        if (resp) {
          if (resp.message === 'success') {
              this.carts = [];
              const dataList = resp.cartList;
              for (var i = 0; i < dataList.length; i++) {
                  var item = dataList[i];
                  this.cartModel = new CartModel();
                  this.cartModel.Id = item.id;
                  this.cartModel.ProductId = item.product.id;
                  this.cartModel.Name = item.product.name;
                  this.cartModel.Quantity = item.quantity;
                  this.cartModel.Price = item.product.price;
                  this.cartModel.Photo = item.product.photo;
                  this.carts.push(this.cartModel);
                  this.total += item.product.price * item.quantity;
              }
          } else {
              console.log('Cart failed');
          }
        } else {
          console.log('Cart failed');
        }
    });
  }

}
