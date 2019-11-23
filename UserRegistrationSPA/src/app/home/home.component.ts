import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  items: Array<any> = [];
  role: Number;

  constructor() {
    this.role = 1;
    this.items = [
      { name: 'assets/images/pizzapic1.jpg'},
      { name: 'assets/images/pizzapic2.jpg'},
      { name: 'assets/images/pizzapic3.jpg'},
      { name: 'assets/images/pizzapic1.jpg'},
      { name: 'assets/images/pizzapic2.jpg'},
      { name: 'assets/images/pizzapic3.jpg'}
    ];
  }

  ngOnInit() {
  }

}
