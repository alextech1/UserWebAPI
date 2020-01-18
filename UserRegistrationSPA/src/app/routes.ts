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
import { AddCartComponent } from './add-cart/add-cart.component';
import { EditCartComponent } from './edit-cart/edit-cart.component';
import { FinanceChartComponent } from './finance-chart/finance-chart.component';

export const appRoutes: Routes = [
  {
    path: '', 
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full'},
      { path: 'home', component: HomeComponent},
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
      { path: 'admin/login', component: AdminLoginComponent},
      { path: 'add-cart', component: AddCartComponent },
      { path: 'edit-cart', component: EditCartComponent },
      { path: 'finance-chart', component: FinanceChartComponent }
    ]
  }
]; 
 


//export class AppRoutingModule { }
