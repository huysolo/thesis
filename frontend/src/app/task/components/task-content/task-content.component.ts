import { Component, OnInit } from '@angular/core';
import {TaskCreateComponent} from '../task-create/task-create.component';
import {TaskInfo} from '../task-info';
import { TaskService } from '../../task.service';

@Component({
  selector: 'app-task-content',
  templateUrl: './task-content.component.html',
  styleUrls: ['./task-content.component.css']
})
export class TaskContentComponent implements OnInit {
  showFiller: Boolean ;
  private taskList: Array<any> = [{title: 'AAA', description: 'BBB', deadline: 'CCC', student: ['Min', 'Min1', 'Min2', 'Min3']}];
  constructor(public taskService: TaskService) {
    this.showFiller = false;
    //console.log(this.taskService.taskList);
   }

  ngOnInit() {
  }

}
