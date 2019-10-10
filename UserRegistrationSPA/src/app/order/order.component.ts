import { Component, OnInit } from '@angular/core';
import { Product } from '../entities/product.entity';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  private products: Product[];
  
  constructor( private productService: ProductService ) { 
    
  }

  ngOnInit() {
    this.products = this.productService.findAll();
    console.log(this.products);

  }

}
