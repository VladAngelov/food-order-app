import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormsModule,
  ReactiveFormsModule
} from '@angular/forms';

import { ProductListComponent } from 'src/app/components/product/product-list/product-list.component';
import { ProductAddComponent } from 'src/app/components/product/product-add/product-add.component';
import { ProductEditComponent } from 'src/app/components/product/product-edit/product-edit.component';
import { ProductRoutingModule } from './product-routing.module';
import { ProductService } from 'src/app/_services/product/product.service';
import { ImageService } from 'src/app/_services/image/image.service';


@NgModule({
  declarations: [
    ProductListComponent,
    ProductAddComponent,
    ProductEditComponent
  ],
  imports: [
    CommonModule,
    ProductRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    ProductService,
    ImageService
  ]
})
export class ProductModule { }
