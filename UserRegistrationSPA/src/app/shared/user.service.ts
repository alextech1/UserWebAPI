import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import { User } from './user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  readonly rootUrl = "http://localhost:5000/" //connect to API
  constructor(private http: HttpClient) { }

  registerUser(user : User){
    const body : User = {
      FirstName:user.FirstName,
      LastName:user.LastName,
      UserName: user.UserName,
      Password: user.Password,
      Email: user.Email      
    }
    return this.http.post(this.rootUrl + 'api/User/Register', body);
  }
}

