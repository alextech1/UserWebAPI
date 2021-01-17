import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../services/product.service';
import { Router } from '@angular/router';
import { UserService } from '../shared/user.service';
import { ToastrService } from 'ngx-toastr';
import { User } from '../shared/user.model';
import { RoleService } from '../services/role.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  private adminId: string;

  constructor(private activatedRoute: ActivatedRoute,
    private productService: ProductService,
    private router: Router,
    private toastr: ToastrService,
    private userService: UserService,
    private roleService: RoleService,
    private Element: ElementRef) { }
  private users: User[] = [];


  @ViewChild('roleDiv', { static: true }) roleView: ElementRef;
  @ViewChild('orderDiv', { static: true }) orderView: ElementRef;
  @ViewChild('roleMenu', { static: true }) roleMenu: ElementRef;
  @ViewChild('orderMenu', { static: true }) orderMenu: ElementRef;

  ngOnInit() {

    this.activatedRoute.params.subscribe(params => {
      let logout = params['logout'];
      if (logout) {
        localStorage.setItem('adminid', '');
      }
      let id = params['id'];
      console.log('here');
      this.adminId = localStorage.getItem('adminid');
      console.log(this.adminId);
      if (this.adminId == null || this.adminId === '') {
        this.router.navigateByUrl('admin/login');
      }

      this.userService.getAllUsers().subscribe((data: any) => {
        console.log(data);
        for (let i = 0; i < data.users.length; i++) {
          let user: User = new User();
          user.Id = data.users[i].id;
          user.UserName = data.users[i].userName;
          user.FirstName = data.users[i].firstName;
          user.LastName = data.users[i].lastName;
          user.Email = data.users[i].address;
          user.Role = data.users[i].role;
          this.users[i] = user;
        }
        console.log(this.users);
      }, error => {
        this.toastr.error(error);
      });

    });

  }

  onRoleClicked() {
    this.roleView.nativeElement.classList.remove('cls_hide');
    this.orderView.nativeElement.classList.add('cls_hide');

    this.roleMenu.nativeElement.classList.add('actived');
    this.orderMenu.nativeElement.classList.remove('actived');
  }

  onOrderStatusClicked() {

    this.roleView.nativeElement.classList.add('cls_hide');
    this.orderView.nativeElement.classList.remove('cls_hide');
    this.roleMenu.nativeElement.classList.remove('actived');
    this.orderMenu.nativeElement.classList.add('actived');
  }

  onSave() {

  }

  onRoleChanged(userId, role) {
    for (let i = 0; i < this.users.length; i++) {
      if (this.users[i].Id === userId) {
        this.users[i].Role = role;
        this.roleService.saveRole(this.users[i]).subscribe((data: any) => {
        }, error => {
          this.toastr.error(error);
        });
        break;
      }
    }
  }
}
