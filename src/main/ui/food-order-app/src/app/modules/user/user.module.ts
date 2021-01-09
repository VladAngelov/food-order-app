import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormsModule,
  ReactiveFormsModule
} from '@angular/forms';

import { LogInComponent } from 'src/app/components/user/log-in/log-in.component';
import { UserRoutingModule } from './user-routing.module';
import { UserService } from 'src/app/_services/user/user.service';
import { AuthService } from 'src/app/_services/auth/auth.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@NgModule({
  declarations: [
    LogInComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    UserService,
    AuthService,
    TokenStorageService
  ]
})
export class UserModule { }
