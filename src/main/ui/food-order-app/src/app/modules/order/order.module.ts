import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormsModule,
  ReactiveFormsModule
} from '@angular/forms';
import { OrderAddComponent } from 'src/app/components/order/order-add/order-add.component';
import { OrderEditComponent } from 'src/app/components/order/order-edit/order-edit.component';
import { OrderRoutingModule } from './order-routing.module';
import { OrderListComponent } from 'src/app/components/order/order-list/order-list.component';
import { OrderService } from 'src/app/_services/order/order.service';

@NgModule({
  declarations: [
    OrderListComponent,
    OrderAddComponent,
    OrderEditComponent
  ],
  imports: [
    CommonModule,
    OrderRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    OrderService,
  ]
})
export class OrderModule { }
