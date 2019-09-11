import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { LogInComponent } from './log-in/log-in.component';

export const appRoutes: Routes = [
  { path: 'home', component: HomeComponent },
  {
    path: '', 
    children: [
      { path: 'sign-up', component: SignUpComponent},
      { path: 'log-in', component: LogInComponent}
    ]
  },
  { path: '**', redirectTo: 'home', pathMatch: 'full'},
];
 


//export class AppRoutingModule { }
