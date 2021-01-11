import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConstants } from 'src/app/constants/app.constants';
import { IProduct } from 'src/app/interfaces/product';
import { Product } from 'src/app/models/product';


@Injectable()
export class OrderService {
  BASE = AppConstants.BASE_API_URL;
  ADD = AppConstants.ORDER_MAKE;
  products: Product[] = [];

  constructor(private http: HttpClient) { }

  transfer(productsInOrder: IProduct[]) {
    this.products = productsInOrder;

    console.log('Products in service -->>', this.products);
  }

  getProducts() {
    return this.products;
  }

  // addOrder(order) {
  //   this.http.post();
  // }

}
