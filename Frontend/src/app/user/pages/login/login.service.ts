import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CurrUserInfo } from './curr-user-info';
import { AuthService } from '../../../core/auth.service';
import { Router } from '@angular/router';
@Injectable()
export class LoginService {

  constructor(private httpClient: HttpClient, private authService: AuthService, 
  private router: Router
  ) { }

  LoginService(form) {
    let loginUrl = `http://localhost:8080/login`;
    this.httpClient.post<CurrUserInfo>(loginUrl, {
      username: form.username,
      password: form.password
    }).subscribe(
      res => {
        if (res != null) {
          localStorage.setItem("isLogin", "true");
          localStorage.setItem("token", res.token);
          localStorage.setItem("username", res.username);
          localStorage.setItem("isStudent", (res.isStudent == true) ? "true" : "false");
        }
        else {
          console.log("Wrong account");
          localStorage.setItem("isLogin", "false");
        }
      },
      err => {
        console.log(err);
      }
      );
  }

}
