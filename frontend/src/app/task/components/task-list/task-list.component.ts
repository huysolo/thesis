import { Component, OnInit } from '@angular/core';
import {TaskService} from '../../task.service';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {
  // listSem: Array<any>;
  // listTopic: Array<any>;
  // semID: any;
  // topicID: any;

  constructor(public taskService: TaskService) {
    
  }

  ngOnInit() {
  }



  // getPage() {
  //   console.log(this.topicID);
  //   this.taskService.getPage(this.topicID, 0).subscribe(
  //     res => {
  //       console.log(res);
  //     }
  //   );
  // }

}
