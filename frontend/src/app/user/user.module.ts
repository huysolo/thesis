import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './pages/login/login.component';
import { ManageAccountComponent } from './pages/manage-account/manage-account.component';


@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [LoginComponent, ManageAccountComponent]
})
export class UserModule { }
