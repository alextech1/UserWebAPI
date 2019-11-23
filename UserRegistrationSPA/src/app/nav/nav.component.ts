import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  role: string;
  constructor() { }

  ngOnInit() { 
    this.role = localStorage.getItem("role");
    console.log("fe");
    console.log(this.role);
  }

}
