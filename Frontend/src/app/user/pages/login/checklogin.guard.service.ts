import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router/src/interfaces';
import { AuthService } from '../../../core/auth.service';

@Injectable()
export class CheckloginGuard implements CanActivate  {

  constructor() { }

  canActivate(){
    let isLogin = (localStorage.getItem('isLogin')=='true')? true:false;
    return isLogin;
  }

}
