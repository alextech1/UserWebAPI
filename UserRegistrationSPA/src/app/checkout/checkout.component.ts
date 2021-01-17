import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    this.removeAll();
  }

  removeAll(): void{
    let userId = localStorage.getItem('username');
    localStorage.removeItem(userId.toString());
    // localStorage.setItem(userId.toString(), JSON.stringify(""));
  }

}
