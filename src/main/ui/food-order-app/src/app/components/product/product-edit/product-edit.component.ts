import {
  Component,
  OnInit
} from '@angular/core';
import {
  FormControl,
  FormGroup
} from '@angular/forms';
import {
  ActivatedRoute,
  Router
} from '@angular/router';

import { AppConstants } from 'src/app/constants/app.constants';
import { ProductType } from 'src/app/enums/product-type';
import { ImageService } from 'src/app/_services/image/image.service';
import { ProductService } from 'src/app/_services/product/product.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {

  foodTypes = ProductType;
  selectedFile: File;
  message: string;

  id: string;
  product: any;
  isLoading = false;

  HOME = AppConstants.HOME_URL;
  private roles: string[];
  isLoggedIn = false;
  isAdmin = false;
  username: string;

  form = new FormGroup({
    name: new FormControl(''),
    content: new FormControl(''),
    volume: new FormControl(''),
    foodType: new FormControl(''),
    price: new FormControl('')
  });

  constructor(
    private productService: ProductService,
    private router: Router,
    private tokenStorageService: TokenStorageService,
    private activatedRoute: ActivatedRoute,
    private imageService: ImageService
  ) { }

  ngOnInit(): void {
    this.isLoading = true;

    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.isAdmin = this.roles.includes('ROLE_ADMIN');
      this.username = user.displayName;
    }

    this.id = null;
    this.id = this.activatedRoute.snapshot.params.id;
    this.productService.getById(this.id)
      .subscribe(x => this.product = x);

    this.isLoading = false;
  }

  redirecting() {
    if (!this.isAdmin && !this.isLoggedIn) {
      this.router.navigate([this.HOME]);
    }
  }

  submitHandler(): void {
    debugger;

    if (this.form.controls['name'].value != null || undefined) {
      this.product.name = this.form.controls['name'].value;
    }
    if (this.form.controls['content'].value != null || undefined) {
      this.product.content = this.form.controls['content'].value;
    }
    if (this.form.controls['volume'].value != null || undefined) {
      this.product.volume = this.form.controls['volume'].value;
    }
    if (this.form.controls['price'].value != null || undefined) {
      this.product.price = this.form.controls['price'].value;
    }
    if (this.form.controls['foodType'].value != null || undefined) {
      this.product.type = this.form.controls['foodType'].value;
    }
    //product.picture = this.selectedFile
    debugger;
    if (this.selectedFile != null || undefined) {
      this.onUpload(this.product.name);
    }
    this.productService.editProduct(this.product);
    this.router.navigate([this.HOME]);

    // TODO: To optimize
  }

  public onFileChanged(event) {
    this.selectedFile = event.target.files[0];
  }

  onUpload(name: string) {
    console.log(this.selectedFile);
    debugger;
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, name);

    this.imageService.uploadImage(uploadImageData);
  }

}
