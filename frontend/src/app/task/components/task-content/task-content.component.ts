import { Component, OnInit } from '@angular/core';
import {TaskCreateComponent} from '../task-create/task-create.component';
import {TaskInfo} from '../task-info';
import { TaskService } from '../../task.service';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-task-content',
  templateUrl: './task-content.component.html',
  styleUrls: ['./task-content.component.css']
})
export class TaskContentComponent implements OnInit {
  showFiller: Boolean ;
  crttaskForm: FormGroup;
  checkStyle: String;
  listStd = [{name: 'Min', class: ''} , {name: 'Min1', class: ''} , {name: 'Min2', class: ''} , {name: 'Min3', class: ''} ];
  listChecked: String[] = [];
  taskStd: Array<any>;
  private taskList: Array<any> = [{title: 'AAA', description: 'BBB', deadline: 'CCC', student: ['Min', 'Min1', 'Min2', 'Min3']}];
  isCreate: String;

  constructor(public taskService: TaskService, private fb: FormBuilder) {
    this.crttaskForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      deadline: ['2017-11-16T20:00', Validators.required]
    });

    this.taskService.getStdDoTask().subscribe(
      res => {
        console.log(res);
        this.taskStd = res;
        console.log(this.taskStd);
      }
    );
   }

  ngOnInit() {
  }

  checked(list): void {
    if (list.class === undefined) {
      list.class = 'checked';
      this.listChecked.push(list.idStudent);
    } else {
      list.class = undefined;
      for (let i = 0; i < this.listChecked.length; i++) {
        if (this.listChecked[i] === list.idStudent) {
          this.listChecked.splice(i, 1);
        }
      }
    }
    console.log(this.listChecked);
  }

  isCreateTask() {
    if (this.isCreate === 'create_task') {
      this.isCreate = null;
    } else {
      this.isCreate = 'create_task';
    }
  }

  createtask(form) {
    this.taskService.createtask(form, this.listChecked).subscribe(
      res => {
      }
    );
  }

}
