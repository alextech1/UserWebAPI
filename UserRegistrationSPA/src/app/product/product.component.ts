import { Component, OnInit, ViewChild } from '@angular/core';
import { Product } from '../entities/product.entity';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  private productModel: Product;
  private products: Product[] = [];
  private headElements = ['Id', 'Name', 'Photo', 'Price'];

  constructor(
    private productService: ProductService
  ) { }

  ngOnInit() {
    console.log('product nginit');
    this.productService.findAll('').subscribe(resp => {
        console.log('resp', resp);
        if (resp) {
          if (resp.message === 'success') {
              const dataList = resp.productList;
              for (var i = 0; i < dataList.length; i++) {
                  var item = dataList[i];
                  this.productModel = new Product();
                  this.productModel.id = item.Id;
                  this.productModel.name = item.Name;
                  this.productModel.price = item.Price;
                  this.productModel.photo = item.Photo;
                  this.products.push(this.productModel);
              }
          } else {
              console.log('product failed');
          }
        } else {
          console.log('product failed');
        }
    });
    
  }

}
