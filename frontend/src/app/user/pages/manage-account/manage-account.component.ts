import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../core/auth.service';

@Component({
  selector: 'app-manage-account',
  templateUrl: './manage-account.component.html',
  styleUrls: ['./manage-account.component.css']
})
export class ManageAccountComponent implements OnInit {

  constructor(public authService: AuthService) { }

  ngOnInit() {
  }

}
