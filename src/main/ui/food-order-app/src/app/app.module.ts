import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { ProductModule } from './modules/product/product.module';
import { UserModule } from './modules/user/user.module';
import { OrderAddComponent } from './components/order/order-add/order-add.component';
import { OrderEditComponent } from './components/order/order-edit/order-edit.component';
import { ContactsComponent } from './components/contacts/contacts/contacts.component';
import { OrderListComponent } from './components/order/order-list/order-list.component';

@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    HomeComponent,
    OrderAddComponent,
    OrderEditComponent,
    ContactsComponent,
    OrderListComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ProductModule,
    UserModule
  ],
  providers: [],
  bootstrap: [
    AppComponent,
    HeaderComponent,
    FooterComponent
  ]
})
export class AppModule { }
