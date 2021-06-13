import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../shared/user.model';
import { environment } from 'src/environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt'; // npm install this

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl = environment.apiUrl + 'auth/'; // http://localhost:5000/api/auth/
  jwtHelper = new JwtHelperService();
  decodedToken: any;
  currentUser: Observable<User>;
  private currentUserSubject: BehaviorSubject<User>;

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  constructor(private http: HttpClient) {
    // this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('username')));
    // this.currentUser = this.currentUserSubject.asObservable();
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');

    return !this.jwtHelper.isTokenExpired(token);
  }

  login(model: any) {
    return this.http.post(this.baseUrl + 'login', model).pipe( // api/auth/login
      map((response: any) => {
        const user = response;
        console.log(response);
        if (user) {
          localStorage.setItem('address', user.address);
          localStorage.setItem('token', user.token);
          localStorage.setItem('role', user.role);
          localStorage.setItem('id', user.id);
          // localStorage.setItem('user', JSON.stringify(user.user));
          this.decodedToken = this.jwtHelper.decodeToken(user.token);
          this.currentUser = user.user;
        }
      })
    );
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('id');
    this.currentUserSubject.next(null);
  }

}
