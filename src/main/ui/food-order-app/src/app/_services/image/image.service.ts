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
    debugger;
    // this.http.post(this.BASE + this.IMAGE_POST,
    //   uploadImageData,
    //   {
    //     headers: httpOptions.headers,
    //     observe: 'response'
    //   })
    //   .subscribe(x => {
    //     console.log(x);
    //   });

    this.http.post('http://localhost:8080/api/image/upload',
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
