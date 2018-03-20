import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CurrUserInfo } from './curr-user-info';
import { AuthService } from '../../../core/auth.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'
@Injectable()
export class LoginService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  login(form) {
    let loginUrl = `http://localhost:8080/login`;
    return this.httpClient.post<any>(loginUrl, {
       username: form.username,
        password: form.password 
      })
      .map(res => {
        if (res) {
          localStorage.setItem("isLogin", "true");
          localStorage.setItem("token", res.token);
          localStorage.setItem("username", res.username);
          localStorage.setItem("isStudent", (res.isStudent == true) ? "true" : "false");
        }
      });
  }

}
