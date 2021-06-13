import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { DataTableModule } from 'ng-angular8-datatable';

import { createCustomElement } from '@angular/elements';

import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { LogInComponent } from './log-in/log-in.component';
import { HomeComponent } from './home/home.component';
import { CarouselComponent } from './carousel/carousel.component';
import { NavComponent } from './nav/nav.component';
import { RouterModule } from '@angular/router';
import { appRoutes } from './routes';
import { ProductComponent } from './product/product.component';
import { OrderComponent } from './order/order.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { LogoutComponent } from './logout/logout.component';
import { AdminComponent } from './admin/admin.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { ProfileComponent } from './profile/profile.component';
import { StoreComponent } from './store/store.component';
import { AddCartComponent } from './add-cart/add-cart.component';
import { EditCartComponent } from './edit-cart/edit-cart.component';
import { ChartModule, LineSeriesService, CategoryService, } from '@syncfusion/ej2-angular-charts';
import { FinanceChartComponent } from './finance-chart/finance-chart.component';
import { NgbModule, NgbCarousel, NgbCarouselModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './footer/footer.component';
import { SearchComponent } from './search/search.component';
import { FilterPipe } from './pipes/filter.pipe';
import { AgmCoreModule } from '@agm/core';


@NgModule({
   declarations: [			
      AppComponent,
      FilterPipe,
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
      StoreComponent,
      AddCartComponent,
      EditCartComponent,
      FinanceChartComponent,
      CarouselComponent,
      FooterComponent,
      StoreComponent,
      SearchComponent
      
   ],
   imports: [
      HttpModule,
      BrowserModule,
      HttpClientModule,
      FormsModule,
      ReactiveFormsModule,
      CommonModule,
      BrowserAnimationsModule,
      NgbCarouselModule,
      NgbModule,
      ToastrModule.forRoot(),
      RouterModule.forRoot(appRoutes),
      DataTableModule,
      ChartModule,
      AgmCoreModule.forRoot({
         apiKey: 'api_key'
      })
   ],
   providers: [
      LineSeriesService,
      CategoryService
   ],
   bootstrap: [
      AppComponent
   ]
})

export class AppModule {
   constructor() {
   }
   ngDoBootstrap() {}

}
