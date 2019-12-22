import { Component, OnInit, ViewChild, Output, ElementRef, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  @Output() navHeight: EventEmitter<number> = new EventEmitter();
  @ViewChild('navbar') navbar: ElementRef;

  role: string;
  constructor() { }

  ngOnInit() { 
    this.navHeight.next(this.navbar.nativeElement.offsetHeight);
    this.role = localStorage.getItem("role");
    console.log("fe");
    console.log(this.role);
  }

}
