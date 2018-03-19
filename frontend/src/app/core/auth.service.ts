import { Injectable } from '@angular/core';
import { CurrUserInfo } from '../user/pages/login/curr-user-info';
import { OnInit } from '@angular/core/src/metadata/lifecycle_hooks';

@Injectable()
export class AuthService{
  isLogin :boolean;
  username: String;
  isStudent: Boolean;
  token: string;
  constructor() {
    this.isLogin = (localStorage.getItem('isLogin') == 'true')? true:false;
    this.username = localStorage.getItem('username');
    this.token = localStorage.getItem('token');
    this.isStudent = (localStorage.getItem('isStudent') == 'true')? true: false;
  }


}
