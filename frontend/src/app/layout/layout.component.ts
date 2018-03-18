import { Component, OnInit } from '@angular/core';
import {AuthService} from '../core/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  username: String;
  constructor(private authService: AuthService, private router: Router) {
    this.username = authService.username;
  }

  ngOnInit() {
   
  }

  Logout(){
    localStorage.clear();
    this.router.navigate(['/user/login']);
    window.location.reload();
  }

}
