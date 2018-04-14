import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TaskInfo } from './components/task-info';

@Injectable()
export class TaskService {
  taskStdList: Array<any>;

  constructor(private httpClient: HttpClient) { }

  createtask(task: TaskInfo) {
    const loginUrl = `http://localhost:8080/crttask`;
    return this.httpClient.post<TaskInfo>(loginUrl, {
      title: task.title,
      description: task.description,
      deadline: task.deadline,
      student: task.student
    });
  }

  getlistTask(topicID: number) {
    const loginUrl = `http://localhost:8080/getlisttask`;
    return this.httpClient.get<any>(loginUrl + '?topicID=' + topicID);
  }

  getAllStudentDoTopic() {
    const loginUrl = `http://localhost:8080/getallstd`;
    return this.httpClient.post<any>(loginUrl, {});
  }

  submitTask(taskID: number, submit: number) {
    const loginUrl = `http://localhost:8080/submittask`;
    return this.httpClient.get<any>(loginUrl + '?taskID=' + taskID + '&submit=' + submit + '');
  }

  reviewTask(taskID: number, pass: number) {
    const loginUrl = `http://localhost:8080/reviewtask`;
    return this.httpClient.get<any>(loginUrl + '?taskID=' + taskID + '&pass=' + pass + '');
  }

}
