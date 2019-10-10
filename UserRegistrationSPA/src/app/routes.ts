import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { LogInComponent } from './log-in/log-in.component';
import { ProductComponent } from './product/product.component';
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { LogoutComponent } from './logout/logout.component';

export const appRoutes: Routes = [
  { path: 'home', component: HomeComponent },
  {
    path: '', 
    children: [
      { path: 'sign-up', component: SignUpComponent},
      { path: 'log-in', component: LogInComponent},
      { path: 'order', component: OrderComponent},
      { path: 'cart', component: CartComponent},
      { path: 'product', component: ProductComponent},
      { path: 'checkout', component: CheckoutComponent},
      { path: 'logout', component: LogoutComponent}
    ]
  },
  { path: '**', redirectTo: 'home', pathMatch: 'full'},
]; 
 


//export class AppRoutingModule { }
