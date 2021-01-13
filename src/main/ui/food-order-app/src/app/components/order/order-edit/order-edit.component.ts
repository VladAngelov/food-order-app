import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppConstants } from 'src/app/constants/app.constants';
import { IOrder } from 'src/app/interfaces/order';
import { OrderService } from 'src/app/_services/order/order.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@Component({
  selector: 'app-order-edit',
  templateUrl: './order-edit.component.html',
  styleUrls: ['./order-edit.component.css']
})
export class OrderEditComponent implements OnInit {

  private roles: string[];
  isLoggedIn = false;
  isAdmin = false;
  isLoading = false;

  message = '';
  order: IOrder;
  id: string;

  form = new FormGroup({
    status: new FormControl(''),
  });

  constructor(
    private orderService: OrderService,
    private tokenStorageService: TokenStorageService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
  ) {
    this.id = null;
    this.id = activatedRoute.snapshot.params.id;
  }

  ngOnInit(): void {
    this.isLoading = true;
    this.orderService.getById(this.id)
      .subscribe(order => {
        this.order = order;
      }, err => {
        this.message = err.message;
        console.log('ERROR -->> ', err.message);
      }, () => {
        console.log('Order -->> ', this.order);
      });

    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.isAdmin = this.roles.includes('ROLE_ADMIN');
    }
    this.redirecting();
    this.isLoading = false;
  }

  submitHandler(): void {
    this.isLoading = true;
    this.order.active = this.form.controls['status'].value;

    this.orderService.editOrder(this.order)
      .subscribe(order => {
        this.order = order;
      }, err => {
        this.message = err.message;
        console.log('ERROR -->> ', err.message);
      }, () => {
        this.isLoading = false;
        this.router.navigate([AppConstants.HOME_URL]);
      });
  }

  redirecting() {
    if (!this.isLoggedIn && !this.isAdmin) {
      this.router.navigate([AppConstants.HOME_URL]);
    }
  }

}
