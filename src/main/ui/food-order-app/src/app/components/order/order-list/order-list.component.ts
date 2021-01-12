import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppConstants } from 'src/app/constants/app.constants';
import { IOrder } from 'src/app/interfaces/order';
import { OrderService } from 'src/app/_services/order/order.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  private roles: string[];
  isLoggedIn = false;
  isAdmin = false;
  orders: IOrder[] = [];

  activeOrders = this.orders.filter(o => o.isActive);
  deactiveOrders = this.orders.filter(o => !o.isActive);

  constructor(
    private orderService: OrderService,
    private tokenStorageService: TokenStorageService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.orderService.getAll()
      .subscribe(orders => {
        this.orders = orders;
      })

    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.isAdmin = this.roles.includes('ROLE_ADMIN');
    }
    this.redirecting();
  }

  redirecting() {
    if (!this.isLoggedIn && !this.isAdmin) {
      this.router.navigate([AppConstants.HOME_URL]);
    }
  }

}
