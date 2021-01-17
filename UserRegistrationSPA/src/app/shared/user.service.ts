import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import { User } from './user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  readonly rootUrl = 'http://localhost:5000/';
  constructor(private http: HttpClient) { }

  registerUser(user: User): Observable<User>
  {
    const body: User = {
      // Id: user.Id,
      FirstName: user.FirstName,
      LastName: user.LastName,
      UserName: user.UserName,
      Password: user.Password,
      Email: user.Email,
      Address: user.Address,
      Role: user.Role,
      OrderStatus: 0,
      Token: user.Token
    }
    return this.http.post<User>(this.rootUrl + 'api/User/Register', body);
  }

  getAllUsers()
  {
    return this.http.post<User>(this.rootUrl + 'api/admin/getusers', '');
  }

  getUser(id): Observable<User> {
    return this.http.get<User>(this.rootUrl + 'users/' + id);
  }
}

