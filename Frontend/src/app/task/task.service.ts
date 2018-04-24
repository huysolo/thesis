import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TaskInfo } from './components/task-info';

import * as SockJs from 'sockjs-client';
import * as Stomp from 'stompjs';

@Injectable()
export class TaskService {
  taskStdList: Array<any>;
  private socket;

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

  getPage(topicID: number, pageNumber: number) {
    const loginUrl = `http://localhost:8080/getlisttask`;
    return this.httpClient.get<any>(loginUrl + '?topicID=' + topicID + '&page=' + pageNumber + '');
  }

  getAllStudentDoTopic() {
    const loginUrl = `http://localhost:8080/getallstd`;
    return this.httpClient.get<any>(loginUrl);
  }

  submitTask(taskID: number, submit: number) {
    const loginUrl = `http://localhost:8080/submittask`;
    return this.httpClient.get<any>(loginUrl + '?taskID=' + taskID + '&submit=' + submit + '');
  }

  reviewTask(taskID: number, pass: number) {
    const loginUrl = `http://localhost:8080/reviewtask`;
    return this.httpClient.get<any>(loginUrl + '?taskID=' + taskID + '&pass=' + pass + '');
  }

  getTopicCount() {
    const loginUrl = `http://localhost:8080/topiccount`;
    return this.httpClient.get<any>(loginUrl);
  }

  getSemCount(){
    const Url = `http://localhost:8080/semcount`;
    return this.httpClient.get<any>(Url);
  }

  getTopicFromSemID(semid) {
    const loginUrl = `http://localhost:8080/getlisttopic`;
    return this.httpClient.get<any>(loginUrl + '?semid=' + semid);
  }

  getTopicFromStd(){
    const loginUrl = `http://localhost:8080/stdgetlisttopic`;
    return this.httpClient.get<any>(loginUrl);
  }

  receiveMessage() {
    const socket = new SockJs(`http://localhost:8080/socket`);
    const stompClient = Stomp.over(socket);
    return stompClient;
  }

  sendMessage(message) {
    const loginUrl = `http://localhost:8080/notify`;
    return this.httpClient.get<any>(loginUrl + '?message=' + message);
  }

  getAllMessage() {
    const loginUrl = `http://localhost:8080/getallmessage`;
    return this.httpClient.get<any>(loginUrl);
  }

  getTaskComment(taskID: number) {
    const loginUrl = `http://localhost:8080/gettaskcomment`;
    return this.httpClient.get<any>(loginUrl  +'?taskid=' +taskID);
  }

  sendComment(comment: String, taskid: number){
    const Url = `http://localhost:8080/taskcomment`;
    return this.httpClient.get<any>(Url + '?comment=' + comment + '&taskid=' + taskid + '');
  }
}
