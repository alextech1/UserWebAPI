import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import { User } from '../shared/user.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  readonly rootUrl = "http://localhost:5000/" //connect to API
  constructor(private http: HttpClient) { }

  saveRole(user : User) : Observable<User> 
  {
    const body : User = {
      Id:user.Id,
      FirstName:user.FirstName,
      LastName:user.LastName,
      UserName: user.UserName,
      Password: user.Password,
      Email: user.Email,
      Address: user.Address,
      Role: user.Role,
      OrderStatus: 0,
      Token: user.Token
    }
    console.log("here");
    return this.http.post<User>(this.rootUrl + 'api/admin/saveRole', body);
  }

 
}
