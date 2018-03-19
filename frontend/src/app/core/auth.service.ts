import { Injectable } from '@angular/core';
import { CurrUserInfo } from '../user/pages/login/curr-user-info';
import { OnInit } from '@angular/core/src/metadata/lifecycle_hooks';

@Injectable()

export class AuthService{
  constructor() {   
  }
  public getUsername():string {
    return localStorage.getItem('username');
  }
  public getToken():string {
    return localStorage.getItem('token');
  }
  public isLogin(): boolean {
    return (localStorage.getItem('isLogin') == 'true')? true: false ;
  }
  public isStudent(): boolean {
    return (localStorage.getItem('isStudent') == 'true')? true: false ;
  }
}
