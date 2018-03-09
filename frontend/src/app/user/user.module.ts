import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { ManageAccountComponent } from './manage-account/manage-account.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [LoginComponent, ManageAccountComponent]
})
export class UserModule { }
