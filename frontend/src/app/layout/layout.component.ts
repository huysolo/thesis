import { Component, OnInit } from '@angular/core';
import {AuthService} from '../core/auth.service';
import { Router } from '@angular/router';
import { TopicService } from '../topic/topic.service';
import {TaskService} from '../task/task.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css'],
})
export class LayoutComponent implements OnInit {
  topicCount: Array<any>;
  constructor(public taskService: TaskService, public authService: AuthService, private router: Router, private topicSv: TopicService) {
  }

  ngOnInit() {
    if (this.authService.isProfessor() === true) {
      this.getTopicCount();
    }

  }

  getTopicCount() {
    this.taskService.getTopicCount().subscribe(
      res => {
        console.log(res);
        this.topicCount = res;
      }
    );
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/user/login']);
  }

}
