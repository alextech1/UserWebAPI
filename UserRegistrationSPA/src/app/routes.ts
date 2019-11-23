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
import { AdminComponent } from './admin/admin.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { StoreComponent } from './store/store.component';
import { ProfileComponent } from './profile/profile.component';

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
      { path: 'admin', component: AdminComponent},
      { path: 'checkout', component: CheckoutComponent},
      { path: 'logout', component: LogoutComponent},
      { path: 'profile', component: ProfileComponent},
      { path: 'store', component: StoreComponent},
      { path: 'admin/login', component: AdminLoginComponent}
    ]
  },
  { path: '**', redirectTo: 'home', pathMatch: 'full'},
]; 
 


//export class AppRoutingModule { }
