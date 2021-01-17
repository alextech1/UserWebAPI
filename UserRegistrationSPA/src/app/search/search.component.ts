import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { CartService } from '../services/cart.service';
import { Product } from '../shared/product.model';
import { ProductService } from '../services/product.service';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  private userId: string;
  private productId: number;
  private quantity: number;
  private bodyData: any;
  private addForm: FormGroup;
  private products: Product[] = [];


  constructor(private formBuilder: FormBuilder,
    private cartService: CartService,
    private productService: ProductService,
    private router: Router) { }

  ngOnInit() {
    this.productService.findAll('').subscribe(resp => {
      if (resp) {
        this.products = resp.productList;
      }
    })
  }

}
