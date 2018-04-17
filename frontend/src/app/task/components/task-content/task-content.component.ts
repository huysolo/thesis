import { Component, OnInit } from '@angular/core';
import { TaskInfo } from '../task-info';
import { TaskService } from '../../task.service';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { StudentDoTask } from '../student-do-task';
import { AuthService } from '../../../core/auth.service';


@Component({
  selector: 'app-task-content',
  templateUrl: './task-content.component.html',
  styleUrls: ['./task-content.component.css']
})
export class TaskContentComponent implements OnInit {
  showFiller: Boolean;
  crttaskForm: FormGroup;
  checkStyle: String;
  listChecked: String[] = [];
  listAllStudent: Array<any>;
  listTask: Array<any>;
  isCreate: String;
  isSubmit: String;
  showDropdown: String;

  tempTaskID: number;

  searchText: String = '';

  createTask: TaskInfo;
  listStdDoTask: Array<StudentDoTask> = [];
  public page: number;
  pagecount: Array<number>;




  constructor(public taskService: TaskService, private fb: FormBuilder, public authService: AuthService) {
    this.crttaskForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      deadline: ['2017-11-16T20:00', Validators.required]
    });

    this.taskService.getAllStudentDoTopic().subscribe(
      res => {
        this.listAllStudent = res;
      }
    );

    // this.taskService.getlistTask(1).subscribe(
    //   res => {
    //     this.listTask = res;
    //   }
    // );

    this.isCreate = 'create';
    this.isSubmit = 'submit';
    this.page = 0;
  }

  ngOnInit() {
    this.getPage(1, this.page);
  }

  saveTempTaskID(taskID: number) {
    this.tempTaskID = taskID;
  }

  checked(list): void {
    if (list.class === undefined) {
      list.class = 'checked';
      const temp = <StudentDoTask>({ stdName: list.stdName, archive: null, uploadDate: null });
      this.listStdDoTask.push(temp);
    } else {
      list.class = undefined;
      for (let i = 0; i < this.listStdDoTask.length; i++) {
        if (this.listStdDoTask[i].stdName === list.stdName) {
          this.listStdDoTask.splice(i, 1);
        }
      }
    }
    console.log(this.listStdDoTask);
  }

  isRenderPopup(temp: String, taskID: number) {
    if (temp === 'create') {
      this.isCreate = 'create_task';
    } else if (temp === 'submit') {
      this.saveTempTaskID(taskID);
      this.isSubmit = 'create_task';
    } else if (temp === 'create_task') {
      this.isCreate = 'create';
      this.isSubmit = 'submit';
    }
  }

  createtask(form) {
    const temp = <TaskInfo>({ title: form.title, description: form.description, deadline: form.deadline, student: this.listStdDoTask });
    this.taskService.createtask(temp).subscribe(
      res => {
        this.listTask.push(res);
      }
    );
  }

  showReview() {
    if (this.showDropdown == null) {
      this.showDropdown = 'w3-show';
    } else {
      this.showDropdown = null;
    }
  }

  showTask(task) {
    if (task.showFullTask === undefined) {
      task.showFullTask = true;
    } else {
      task.showFullTask = undefined;
    }
  }

  reviewTask(taskID: number, pass: number) {
    this.taskService.reviewTask(taskID, pass).subscribe(
      res => {
        if (res != null) {
          for (let i = 0; i < this.listTask.length; i++) {
            if (this.listTask[i].taskID === res.idTask) {
              this.listTask[i].pass = res.pass;
            }
          }
        }
      }
    );
  }

  submitToProf() {
    this.taskService.submitTask(this.tempTaskID, 1).subscribe(
      res => {
        if (res != null) {
          for (let i = 0; i < this.listTask.length; i++) {
            if (this.listTask[i].taskID === res.idTask) {
              this.listTask[i].submit = 1;
            }
          }
        }
      }
    );
    this.isSubmit = 'submit';
  }

  getPage(topicID: number, page: number) {
    this.taskService.getPage(topicID, page).subscribe(
      res => {
        this.pagecount = new Array(res.pageCount);
        this.listTask = res.taskList;
      }
    );
  }

  setPage(i: number) {
    this.page = i;
    this.getPage(1, this.page);
  }

}
