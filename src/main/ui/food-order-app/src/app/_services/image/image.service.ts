import {
  HttpClient,
  HttpHeaders
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConstants } from 'src/app/constants/app.constants';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ImageService {
  constructor(private http: HttpClient) { }

  BASE = AppConstants.BASE_API_URL;
  IMAGE_POST = AppConstants.PRODUCT_IMAGE_ADD;
  IMAGE_GET = AppConstants.PRODUCT_IMAGE_GET_BY_NAME;
  message: string;

  uploadImage(uploadImageData) {
    this.http.post(this.BASE + this.IMAGE_POST,
      uploadImageData,
      { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          console.log(this.message = 'Image uploaded successfully');
        } else {
          console.log(this.message = 'Image not uploaded successfully');
        }
      });
  }

  getImage(name) {
    return this.http.get(this.BASE + this.IMAGE_GET + name, httpOptions);
  }

  delete(name: string) {

  }
}
