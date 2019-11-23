import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../services/product.service';
import { Router } from '@angular/router';
import { UserService } from '../shared/user.service';
import { ToastrService } from 'ngx-toastr';
import { User } from '../shared/user.model';
import { RoleService } from '../services/role.service';
import { OrderStatusService } from '../services/orderstatus.service';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {

  constructor( private activatedRoute: ActivatedRoute,
    private productService: ProductService,
    private router: Router,
    private toastr: ToastrService,
    private userService: UserService,
    private roleService: RoleService,
    private orderStatusService: OrderStatusService,
    private Element: ElementRef) { }
    private users: User[] = [];
    private message: string;

  ngOnInit() {

    
    this.userService.getAllUsers().subscribe((data:any) => {  
      console.log(data); 
      var count = 0;
      for(var i = 0; i< data.users.length; i++)
      {
            var user : User = new User();
            user.Id = data.users[i].id;
            user.UserName = data.users[i].userName;
            user.FirstName = data.users[i].firstName;
            user.LastName = data.users[i].lastName;
            user.Email = data.users[i].address;
            user.Role = data.users[i].role;
            if(user.Role == 1)
            {
              this.users[count] = user;
              count ++;
            }
      }
      console.log(this.users);
      
       
    }, error => {
      this.toastr.error(error);
  }); 
  }

  onMessageChanged(messageId)
  {
    this.message = messageId;
    console.log(this.message);
  }
  onSend(userId) 
  {
      console.log(userId);
      this.orderStatusService.addOrderStatus(userId, this.message).subscribe((data:any) => {  
        console.log(data); 
      
         
      }, error => {
        this.toastr.error(error);
    }); 
      this.toastr.success("Message is sent to the user");
  }

}
