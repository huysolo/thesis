import { Component, OnInit, Input } from '@angular/core';
import { Topic } from '../../../models/Topic';
import { TopicService } from '../../topic.service';
import { AuthService } from '../../../core/auth.service';

@Component({
  selector: 'app-topic-content',
  templateUrl: './topic-content.component.html',
  styleUrls: ['./topic-content.component.css']
})
export class TopicContentComponent implements OnInit {
  @Input('topic') topic: Topic;
  @Input('topicNo') topicNo: number;
  @Input('semno') semno: Number;
  constructor(public topicSv: TopicService, public authoSv: AuthService) { }

  ngOnInit() {
  }

  apply() {
    this.topicSv.applyToTopic(this.topic.idTop).subscribe(data => {
      if (data === 'CREATED') {
        this.topicSv.getAppliedTopic(this.semno).subscribe( topic => {
          this.topicSv.appliedTopic = topic;
        });
      }
    });
  }
  reject(){

  }

}
