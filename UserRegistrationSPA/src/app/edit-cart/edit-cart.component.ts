import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-edit-cart',
  templateUrl: './edit-cart.component.html',
  styleUrls: ['./edit-cart.component.css']
})
export class EditCartComponent implements OnInit {
  private editForm: FormGroup;
  private bodyData: any;
  private cartId: number;
  private quantity: number;

  constructor(private formBuilder: FormBuilder, 
    private cartService: CartService,
    private router: Router) { }

  ngOnInit() {
    this.quantity = parseInt(localStorage.getItem('quantity'));
    this.editForm = this.formBuilder.group({
      quantity: ['', Validators.required]
    });
  }

  onSubmit() {
    this.cartId = parseInt(localStorage.getItem('cartId'));
    this.quantity = parseInt(this.editForm.value.quantity);
    this.bodyData = {
      cartId: this.cartId,
      userId: 1, 
      productId: 1,
      quantity: this.quantity
    };
    console.log ("aaa",this.bodyData);
    this.cartService.editCart(this.bodyData).subscribe(resp => {
      if (resp) {
        const message = resp.message;
        this.router.navigate(['cart']);
      }
    });
   
  }
}