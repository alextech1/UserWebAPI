import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../services/product.service';
import { Router } from '@angular/router';
import { UserService } from '../shared/user.service';
import { ToastrService } from 'ngx-toastr';
import { User } from '../shared/user.model';
import { RoleService } from '../services/role.service';
import { OrderStatusService } from '../services/orderstatus.service';
import { AgmCoreModule } from '@agm/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  constructor( private activatedRoute: ActivatedRoute,
    private productService: ProductService,
    private router: Router,
    private toastr: ToastrService,
    private userService: UserService,
    private roleService: RoleService,
    private orderStatusService: OrderStatusService,
    private Element: ElementRef) 
    { }
    private users: User[] = [];
    private message: string;
    private userAddress: string;
    title = 'My first AGM project';
    lat = 51.678418;
    lng = 7.809007;

  ngOnInit() {
    const userId = localStorage.getItem('id');
    console.log('profile id');
    console.log(userId);
    this.userAddress = localStorage.getItem('address');
    // var id = +userId;
    this.orderStatusService.getOrderStatus(userId).subscribe((data: any) => {
      console.log(data);
      this.message = data.message;
    }, error => {
      this.message = 'There is no order status';
  });

  }

}
