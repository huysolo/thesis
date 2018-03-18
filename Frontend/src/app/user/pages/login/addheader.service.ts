import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/observable';
import {AuthService} from '../../../core/auth.service';

@Injectable()
export class AddheaderService implements HttpInterceptor {
  constructor(private authService: AuthService){}
  intercept (req: HttpRequest<any>,next: HttpHandler): Observable<HttpEvent<any>>{
    if(this.authService.token == null){
      this.authService.token = "";
    }
    const authReq = req.clone({
      headers: req.headers.set('token', this.authService.token)
    });
    return next.handle(authReq);
  }
}
