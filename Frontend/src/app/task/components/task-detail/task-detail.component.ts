import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import {AuthService} from '../../../core/auth.service';
import {TaskService} from '../../task.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent implements OnInit {
  listTask: Array<any>;
  issubmit: String;
  @Input('task') task: any;

  constructor(public authService: AuthService, private taskService: TaskService) { 
  }

  ngOnInit() {
    
  }

  showTask(task) {
    if (task.showFullTask === undefined) {
      task.showFullTask = true;
    } else {
      task.showFullTask = undefined;
    }
  }

  isSubmitTask(){
    if(this.issubmit == undefined){
      this.issubmit = 'popup';
    } else {
      this.issubmit = undefined;
    }
  }

  submitToProf() {
    this.taskService.submitTask(this.task.taskID, 1).subscribe(
      res => {
        if (res != null) {
          this.task.submit = 1;
        }
      }
    );
    this.issubmit = undefined;
  }

  getTaskComment() {
    if (this.task.showCmt === undefined) {
      this.task.showCmt = true;
    } else {
      this.task.showCmt = undefined;
    }

  }

  reviewTask(pass: number) {
    this.taskService.reviewTask(this.task.taskID, pass).subscribe(
      res => {
        if (res != null) {
          this.task.pass = res.pass;
        }
      }
    );
  }

}
