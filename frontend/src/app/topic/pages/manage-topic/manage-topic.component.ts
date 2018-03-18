import { Component, OnInit } from '@angular/core';
import { TopicService } from '../../topic.service';
import { Topic } from '../../../models/Topic';
import { Observable } from 'rxjs/Observable';
@Component({
  selector: 'app-manage-topic',
  templateUrl: './manage-topic.component.html',
  styleUrls: ['./manage-topic.component.css']
})
export class ManageTopicComponent implements OnInit {

  public topicLst: Observable<Topic[]>;
  constructor(public topicSv: TopicService) { }

  ngOnInit() {
    this.topicLst = this.topicSv.getListTopic();
    // this.http.get('http://localhost:8080/topic/listTopic').subscribe(data => {
    //   console.log(data);
    // });
  }

}
