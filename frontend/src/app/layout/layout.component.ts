import { Component, OnInit } from '@angular/core';
import {AuthService} from '../core/auth.service';
import { Router } from '@angular/router';
import { TopicService } from '../topic/topic.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  constructor(public authService: AuthService, private router: Router, private topicSv: TopicService) {
  }

  ngOnInit() {

  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/user/login']);
  }

}
