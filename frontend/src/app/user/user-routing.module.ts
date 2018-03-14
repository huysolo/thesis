import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';

const userRotes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(userRotes)
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class UserRoutingModule { }
