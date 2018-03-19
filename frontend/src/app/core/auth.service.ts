import { Injectable } from '@angular/core';
import { CurrUserInfo } from '../user/pages/login/curr-user-info';
import { OnInit } from '@angular/core/src/metadata/lifecycle_hooks';

@Injectable()
export class AuthService {
  constructor() {
  }

  public isLoggedin(): boolean {
    return localStorage.getItem('isLogin') === 'true';
  }

  /**
   * isStudent
   */
  public isStudent() {
    return localStorage.getItem('isStudent') === 'true';
  }

  /**
   * isProffessor
   */
  public isProffessor() {
    return  this.isLoggedin() && !this.isStudent();
  }

  /**
   * getUsername
   */
  public getUsername() {
    return localStorage.getItem('username');
  }

  /**
   * getToken
   */
  public getToken() {
    return localStorage.getItem('token');
  }


}
