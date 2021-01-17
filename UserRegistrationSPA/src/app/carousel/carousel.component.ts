import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbCarousel } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
}) // .map((n) => `https://picsum.photos/id/${n}/900/500`);
export class CarouselComponent implements OnInit {
  // @ViewChild(NgbCarousel)
  // private ngbCarousel: NgbCarousel;
  images = ['../../assets/images/healthimage1.png', '../../assets/images/healthimage2.jpg', '../../assets/images/healthimage3.jpg'];

  constructor() { }

  ngOnInit() {
  }

}
