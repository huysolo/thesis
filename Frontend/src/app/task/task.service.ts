import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TaskInfo } from './components/task-info';

@Injectable()
export class TaskService {
  taskStdList: Array<any>;

  constructor(private httpClient: HttpClient) { }

  createtask(task: TaskInfo) {
    const loginUrl = `http://localhost:8080/crttask`;
    return this.httpClient.post<any>(loginUrl, {
      title: task.title,
      description: task.description,
      deadline: task.deadline,
      student: task.student
    });
  }

  getlistTask() {
    const loginUrl = `http://localhost:8080/getlisttask`;
    return this.httpClient.post<any>(loginUrl, {
      title: 'Min'
    });
  }

  getAllStudentDoTopic() {
    const loginUrl = `http://localhost:8080/getallstd`;
    return this.httpClient.post<any>(loginUrl, {});
  }

}
