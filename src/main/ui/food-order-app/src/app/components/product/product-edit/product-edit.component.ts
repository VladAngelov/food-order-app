import {
  Component,
  OnInit
} from '@angular/core';
import { Router } from '@angular/router';
import { AppConstants } from 'src/app/constants/app.constants';
import { ProductService } from 'src/app/_services/product/product.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {

  HOME = AppConstants.HOME_URL;
  private roles: string[];
  isLoggedIn = false;
  isAdmin = false;
  username: string;

  constructor(
    private productService: ProductService,
    private router: Router,
    private tokenStorageService: TokenStorageService
  ) { }


  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.isAdmin = this.roles.includes('ROLE_ADMIN');
      this.username = user.displayName;
    }
  }
  redirecting() {
    if (!this.isAdmin && !this.isLoggedIn) {
      this.router.navigate[`${this.HOME}`];
    }
  }

}
