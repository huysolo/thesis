import { Component, OnInit } from '@angular/core';
import { TaskCreateComponent } from '../task-create/task-create.component';
import { TaskInfo } from '../task-info';
import { TaskService } from '../../task.service';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { StudentDoTask } from '../student-do-task';

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

  createTask: TaskInfo;
  listStdDoTask: Array<StudentDoTask> = [];


  constructor( public taskService: TaskService, private fb: FormBuilder) {
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

    this.taskService.getlistTask().subscribe(
      res => {
        this.listTask = res;
      }
    );
  }

  ngOnInit() {
  }

  checked(list): void {
    if (list.class === undefined) {
      list.class = 'checked';
      const temp = <StudentDoTask>({stdName: list.stdName, archive: null, uploadDate: null});
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

  isCreateTask() {
    if (this.isCreate === 'create_task') {
      this.isCreate = null;
    } else {
      this.isCreate = 'create_task';
    }
  }

  createtask(form) {
    const temp = <TaskInfo>({title: form.title, description: form.description, deadline: form.deadline, student: this.listStdDoTask});
    this.taskService.createtask(temp).subscribe(
      res => {
        this.listTask.push(res);
      }
    );
  }

}
