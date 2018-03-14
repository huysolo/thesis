import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './pages/login/login.component';
import { ManageAccountComponent } from './pages/manage-account/manage-account.component';
import { UserRoutingModule } from './user-routing.module';


@NgModule({
  imports: [
    CommonModule,
    UserRoutingModule
  ],
  declarations: [
    LoginComponent,
    ManageAccountComponent
  ]
})
export class UserModule { }
