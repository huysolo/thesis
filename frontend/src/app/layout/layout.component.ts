import { Component, OnInit } from '@angular/core';
import {AuthService} from '../core/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  constructor(public authService: AuthService, private router: Router) {
  }

  ngOnInit() {

  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/user/login']);
  }

}
