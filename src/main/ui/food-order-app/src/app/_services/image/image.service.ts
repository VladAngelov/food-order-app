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

  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;

  uploadImage(uploadImageData) {
    this.http.post(this.BASE + this.IMAGE_POST,
      uploadImageData,
      httpOptions)
      .subscribe(x => {
        console.log(x);
      });
  }

  getImage(name) {
    //Make a call to Sprinf Boot to get the Image Bytes.

    this.http.get(this.BASE + this.IMAGE_GET + name, httpOptions)
      .subscribe(
        res => {
          this.retrieveResonse = res;
          this.base64Data = this.retrieveResonse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        }
      );
    return this.retrievedImage;
  }

  delete(name: string) {

  }
}
