import { Component, OnInit } from '@angular/core';
import { User } from '../shared/user.model';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AdminauthService } from '../services/adminauth.service';
import {ToastrService} from 'ngx-toastr';
import { UserService } from '../shared/user.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {
 
    user:User;
    userLoginForm: FormGroup;
    returnUrl: string;
    loading = false;
 
    constructor(private userService:UserService, 
      private fb: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private authService: AdminauthService,
      private toastr: ToastrService,
      private activatedRoute: ActivatedRoute,
    ) {
      // if (this.authService.currentUserValue) {
      //  this.router.navigate(['/']);
      // }
    }

  ngOnInit() {this.activatedRoute.params.subscribe(params => {
    const logout = params['logout'];
    console.log('logout now');
    if (logout) {        
      localStorage.removeItem('username');
      localStorage.removeItem('role');
      localStorage.removeItem('id');
      localStorage.removeItem('token');
      localStorage.removeItem('adminid');
      console.log('logout success');
    }

    const userId = localStorage.getItem('username');
    if (!userId) {
      this.router.navigateByUrl('admin/login');
    } else if (userId) {
      this.router.navigateByUrl('logout');
    }
    this.createLoginForm();
  });
    this.createLoginForm();
  }

  createLoginForm() {
    this.userLoginForm = this.fb.group({
      email:  ['', Validators.required],
      Password:  ['', Validators.required]
  });
  this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
} 

  get f() { return this.userLoginForm.controls; }

  onSubmit() {
    let request: User = new User();
    request.UserName = this.userLoginForm.get('email').value;
    request.Password = this.userLoginForm.get('Password').value;
    console.log(request.UserName);
    if (this.userLoginForm.valid) {
      this.loading = true;
      // this.user = Object.assign({}, this.userLoginForm.value);
      this.authService.login(request)
        .pipe(first())
        .subscribe(
          (data: any) => {
            this.toastr.success('Login Successful');
            localStorage.setItem('username', '');
            localStorage.setItem('role', '0');
            localStorage.setItem('adminid', this.userLoginForm.get('email').value);
            console.log(localStorage.getItem('adminid'));
            location.href = '/admin';
          }
        ,
        error => {
        this.toastr.error('Login failed!');
        this.loading = false;
    }); 
  }
  }


}
