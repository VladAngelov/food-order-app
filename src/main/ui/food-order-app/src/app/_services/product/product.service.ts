import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstants } from 'src/app/constants/app.constants';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ProductService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(AppConstants.BASE_API_URL + AppConstants.PRODUCT_ALL_URL, httpOptions);
  }

  addProduct(product) {
    return this.http.post(AppConstants.BASE_API_URL + AppConstants.PRODUCT_ADD_URL, product, httpOptions);
  }

  getById(id: string) {
    return this.http.get(AppConstants.BASE_API_URL + AppConstants.PRODUCT_BY_ID + id, httpOptions);
  }

  editProduct(product) {
    return this.http.put(AppConstants.BASE_API_URL + AppConstants.PRODUCT_EDIT_BY_ID, product, httpOptions);
  }

  deleteProduct(id: string) {
    return this.http.post(AppConstants.BASE_API_URL + AppConstants.PRODUCT_DELETE_BY_ID, id, httpOptions);
  }

}
