import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbCarousel, NgbSlideEvent, NgbSlideEventSource } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],

})
export class HomeComponent implements OnInit {
  title: string;
  description: string;
  role: Number;
  public selectedindex: number = 0;
  public images = ['../../assets/images/healthimage1.png', '../../assets/images/healthimage2.jpg', '../../assets/images/healthimage3.jpg'];
  
  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router) {
    this.role = 1;
  }

  ngOnInit() {
    //this.showSlides();
  }

  selectImage(index: number) {
    console.log("Index: " + index);
    this.selectedindex = index;
    console.log("Selected Index: " + this.selectedindex);
  }

  showSlides() {
    let i;
    let slides = document.getElementsByClassName("mySlides");
    let dots = document.getElementsByClassName("dot");
    for (i = 0; i < slides.length; i++) {
      (<HTMLElement>slides[i]).style.display = "none";
    }
    this.selectedindex++;
    if (this.selectedindex > slides.length) { this.selectedindex = 1 }
    for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
    }
    (<HTMLElement>slides[this.selectedindex - 1]).style.display = "block";
    dots[this.selectedindex - 1].className += " active";
    setTimeout(() => this.showSlides, 2000);
  }


  

}
