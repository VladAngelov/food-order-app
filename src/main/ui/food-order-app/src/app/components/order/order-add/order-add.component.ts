import {
  Component,
  OnInit
} from '@angular/core';
import {
  FormControl,
  FormGroup,
  Validators
} from '@angular/forms';
import { Router } from '@angular/router';

import { IProduct } from 'src/app/interfaces/product';
import { Order } from 'src/app/models/order';
import { OrderService } from 'src/app/_services/order/order.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@Component({
  selector: 'app-order-add',
  templateUrl: './order-add.component.html',
  styleUrls: ['./order-add.component.css']
})
export class OrderAddComponent implements OnInit {
  isLoading = false;
  minPrice = 5;
  fee = 0.0;
  products: IProduct[] = [];
  isLoggedIn = false;
  roles = [];
  isAdmin = false;
  userData = '';

  form = new FormGroup({
    address: new FormControl('', [Validators.required, Validators.minLength(3)])
  });

  constructor(
    private orderService: OrderService,
    private router: Router,
    private tokenStorageService: TokenStorageService,
  ) { }

  ngOnInit(): void {
    this.isLoading = true;

    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.isAdmin = this.roles.includes('ROLE_ADMIN');
      this.userData = JSON.stringify(user);
    }
    this.products = this.orderService.products;
    this.calcFee();
  }

  calcFee() {
    for (var product of this.products) {
      this.fee = this.fee + product.price;
    }

    this.isLoading = false;
  }

  remove(product) {
    let index = this.products.indexOf(product);
    this.products.splice(index, 1);
  }

  onSubmit() {
    let order = new Order();
    order.address = this.form.controls['address'].value;
    order.date = new Date().toLocaleDateString();
    order.isActive = true;
    order.products = this.products;
    order.sum = this.fee;
    order.userData = this.userData;

    this.orderService.addOrder(order);
  }

  onCancel() {
    this.products = null;
    this.router.navigate(['/']);
  }


}
