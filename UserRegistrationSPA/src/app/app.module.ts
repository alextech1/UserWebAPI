import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { createCustomElement } from '@angular/elements';

import { HttpClientModule } from '@angular/common/http';
//import { AppRoutingModule } from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { ToastrModule } from 'ngx-toastr';
import { LogInComponent } from './log-in/log-in.component';
import { HomeComponent } from './home/home.component';
import { NavComponent } from './nav/nav.component';
import { RouterModule } from '@angular/router';
import { appRoutes } from './routes';
import { Ng2CarouselamosModule } from 'ng2-carouselamos';
import { ProductComponent } from './product/product.component';
import { OrderComponent } from './order/order.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { LogoutComponent } from './logout/logout.component';
import { AdminComponent } from './admin/admin.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { ProfileComponent } from './profile/profile.component';
import { StoreComponent } from './store/store.component';

@NgModule({
   declarations: [
      AppComponent,
      SignUpComponent,
      LogInComponent,
      HomeComponent,
      NavComponent,
      ProductComponent,
      OrderComponent,
      CartComponent,
      CheckoutComponent,
      LogoutComponent,
      AdminComponent,
      AdminLoginComponent,
      ProfileComponent,
      StoreComponent
   ],
   imports: [
      BrowserModule,
      HttpClientModule,
      //AppRoutingModule,
      FormsModule,
      ReactiveFormsModule,
      //REQUIREDforngModeltags\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\nHttpClientModule',
      BrowserAnimationsModule,
      ToastrModule.forRoot(),
      RouterModule.forRoot(appRoutes),
      Ng2CarouselamosModule
   ],
   providers: [],
   bootstrap: [
      AppComponent
   ]
})

export class AppModule { 
   constructor() {
   }
   ngDoBootstrap() {}

}
