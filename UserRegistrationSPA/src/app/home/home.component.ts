import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],

})
export class HomeComponent implements OnInit {
  title: string;
  description: string;
  role: Number;


  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router) {   
    this.role = 1;

  }

  ngOnInit() {
  }

}
