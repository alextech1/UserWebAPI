import { Component, OnInit, ViewChild, Output, ElementRef, EventEmitter } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  //@Output() navHeight: EventEmitter<number> = new EventEmitter();
  //@ViewChild('navbar', {static: true}) navbar: ElementRef;

  searchInput: string = '';
  role: string;
  authenticated: boolean;

  constructor(private authService: AuthService, private router: Router,) {}

  ngOnInit() {
    //this.navHeight.next(this.navbar.nativeElement.offsetHeight);
    this.role = localStorage.getItem('role');
    console.log(this.role);
    this.authenticated = this.authService.isAuthenticated();

    if (this.authenticated === true)
    {
      console.log('authenticated is true');
    }
  }

  

  onSubmit(inputStr: string) {
    console.log('onSubmit')
    this.router.navigate(['/search'], { queryParams: { searchStr: inputStr } });

  }

}
