import { Component, OnInit } from '@angular/core';
import { TopicService } from '../../topic.service';
import { Topic } from '../../../models/Topic';
import { Observable } from 'rxjs/Observable';
import { CommonService } from '../../../core/common.service';
import { Semester } from '../../../models/Semester';

@Component({
  selector: 'app-manage-topic',
  templateUrl: './manage-topic.component.html',
  styleUrls: ['./manage-topic.component.css']
})
export class ManageTopicComponent implements OnInit {
  public listSem: Observable<Semester[]>;
  public topicLst: Observable<Topic[]>;
  constructor(public topicSv: TopicService, public commonSv: CommonService) { }

  ngOnInit() {
    this.topicLstInterval();
    this.listSem = this.commonSv.getListSemester();
  }

  topicLstInterval() {
    // setInterval(() => {
      this.topicLst = this.topicSv.getListTopic();
    // }, 300000);
  }



}
