import { Component, OnInit } from '@angular/core';
import { TaskInfo } from '../task-info';
import { TaskService } from '../../task.service';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { StudentDoTask } from '../student-do-task';
import { AuthService } from '../../../core/auth.service';
import { ActivatedRoute } from '@angular/router';
import { TaskDetailComponent } from '../task-detail/task-detail.component';


@Component({
  selector: 'app-task-content',
  templateUrl: './task-content.component.html',
  styleUrls: ['./task-content.component.css']
})
export class TaskContentComponent implements OnInit {
  listTask: Array<any>;

  searchText: String = '';
  public page: number;
  pagecount: Array<number>;

  disconnection: any;

  type: String;
  isrecent: boolean;
  ishistory: boolean;
  listTopic: Array<any>;
  listSem: Array<any>;

  topicID: any;
  isCreateTask: Boolean;



  constructor(public taskService: TaskService, public authService: AuthService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.page = 0;
    this.route.params.subscribe(params => {
      this.type = params['typ'];
      if (this.type === 'recent') {
        this.listTask = null;
        this.page = 0;
        this.isrecent = true;
        this.ishistory = false;
        if (this.isrecent == true) {
          if (this.authService.isStudent() == true) {
            this.getPage(-1, this.page);
          } else {
            this.getTopicFromSemID(-1);
          }
        }
      } else {
        this.listTask = null;
        this.page = 0;
        this.isrecent = false;
        this.ishistory = true;
        if (this.authService.isProfessor() == true) {
          this.getSem();
        } else {
          this.stdGetListTopic();
        }
      }
    });
  }

  getPage(topicID: number, page: number) {
    if (topicID != 0) {
      this.taskService.getPage(topicID, page).subscribe(
        res => {
          if (res == null) {
            console.log('We dont have Semester now');
          } else {
            this.pagecount = new Array(res.pageCount);
            this.listTask = res.taskList;
            this.topicID = topicID;
          }
        }
      );
    }
  }

  setPage(event: number) {
    this.page = event;
    this.getPage(this.topicID, this.page);
  }

  getTopicFromSemID(semid) {
    this.taskService.getTopicFromSemID(semid).subscribe(
      res => {
        if (res == null) {
          console.log('We dont have Semester now');
        } else {
          this.listTopic = res;
        }
      }
    );
  }

  getSem() {
    this.taskService.getSemCount().subscribe(
      res => {
        this.listSem = res;
      }
    );
  }


  stdGetListTopic() {
    this.taskService.getTopicFromStd().subscribe(
      res => {
        this.listTopic = res;
      }
    );
  }

  createTask() {
    this.isCreateTask = true;
  }

  switchIsCreate(event: Boolean) {
    this.isCreateTask = event;
  }

  addNewTask(event: any) {
    this.listTask.push(event);
    this.isCreateTask = false;
  }

}
