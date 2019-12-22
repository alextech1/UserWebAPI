import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  navbarTopMargin: number = 0;

  onNavHeight(topMargin: number) {
    console.log(topMargin);
    this.navbarTopMargin = topMargin;
  }

  title = 'user-registration';
}
