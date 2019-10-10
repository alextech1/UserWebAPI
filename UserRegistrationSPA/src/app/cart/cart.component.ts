import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../entities/product.entity';
import { Item } from '../entities/item.entity';
import { ProductService } from '../services/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  private items: Item[] = [];
  private total: number = 0;
  private userId: string;
  constructor(
    private activatedRoute: ActivatedRoute,
    private productService: ProductService,
    private router: Router
  ) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      var id = params['id'];
      this.userId = localStorage.getItem("username");
      if(this.userId == "")
      {
        alert("you should login first");
        this.router.navigateByUrl('log-in');
      }
      console.log(this.userId);
      if (id) {
        var item: Item = {
          product: this.productService.find(id),
          quantity: 1            
          };
          if (localStorage.getItem(this.userId.toString()) == null) {
            let cart: any = [];
            cart.push(JSON.stringify(item));
            localStorage.setItem(this.userId, JSON.stringify(cart));
          } else {
            let cart: any = JSON.parse(localStorage.getItem(this.userId.toString()));
            let index: number = -1;
            for (var i = 0; i < cart.length; i++) {
              let item: Item = JSON.parse(cart[i]);
              if(item.product.id == id) {
                index = i;
                break;
              }
            }
            if (index == -1) {
              cart.push(JSON.stringify(item));
              localStorage.setItem(this.userId.toString(), JSON.stringify(cart));
            } else {
              let item: Item = JSON.parse(cart[index]);
              item.quantity += 1;
              cart[index] = JSON.stringify(item);
              localStorage.setItem(this.userId.toString(), JSON.stringify(cart));
            }
          }
          this.loadCart();
        } else {
          this.loadCart();
        }
    });
  }

  loadCart(): void {
    this.total = 0;
    this.items = [];
    console.log(this.userId);
    let cart = JSON.parse(localStorage.getItem(this.userId.toString()));
    for (var i = 0; i < cart.length; i++) {
      let item = JSON.parse(cart[i]);
      this.items.push({
        product: item.product,
        quantity: item.quantity
      });
      this.total += item.product.price * item.quantity;
    }
  }

  remove(id: string): void {
    let cart: any = JSON.parse(localStorage.getItem(this.userId.toString()));
    let index: number = -1;
    for (var i = 0; i < cart.length; i++) {
      let item: Item = JSON.parse(cart[i]);
      if (item.product.id == id) {
        cart.splice(i, 1);
        break;
      }
    }
    localStorage.setItem(this.userId.toString(), JSON.stringify(cart));
    this.loadCart();

  }


}
