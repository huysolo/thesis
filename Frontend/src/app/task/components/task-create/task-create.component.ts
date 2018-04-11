import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import {TaskService} from '../../task.service';

@Component({
  selector: 'app-task-create',
  templateUrl: './task-create.component.html',
  styleUrls: ['./task-create.component.css']
})
export class TaskCreateComponent implements OnInit {
  crttaskForm: FormGroup;
  checkStyle: String;
  listStd = [{name: 'Min', class: ''} , {name: 'Min1', class: ''} , {name: 'Min2', class: ''} , {name: 'Min3', class: ''} ];
  listChecked: String[] = [];
  taskStd: Array<any>;

  constructor(private fb: FormBuilder, private taskService: TaskService) {
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

  createtask(form) {
    this.taskService.createtask(form, this.listChecked).subscribe(
      res => {
      }
    );
  }

}
