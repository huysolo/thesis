import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TaskInfo } from './components/task-info';

@Injectable()
export class TaskService {
  taskStdList: Array<any>;

  constructor(private httpClient: HttpClient) { }

  createtask(form, listStd) {
    const loginUrl = `http://localhost:8080/crttask`;
    return this.httpClient.post<any>(loginUrl, {
      title: form.title,
      description: form.description,
      deadline: form.deadline,
      student: listStd
    });
  }

  getlistTask() {
    const loginUrl = `http://localhost:8080/getlisttask`;
    return this.httpClient.post<any>(loginUrl, {
      title: 'Min'
    }).subscribe(
      res => {

        this.taskStdList = res;
      }
    );
  }

  getStdDoTask() {
    const loginUrl = `http://localhost:8080/stddotask`;
    return this.httpClient.post<any>(loginUrl, {});
  }

}
