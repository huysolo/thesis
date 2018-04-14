import { Component, OnInit, Input } from '@angular/core';
import { Topic } from '../../../models/Topic';
import { TopicService } from '../../topic.service';
import { AuthService } from '../../../core/auth.service';
import { MatDialog } from '@angular/material';
import { TopicDetail } from '../../../models/TopicDetail';
import { TopidDetailComponent } from '../topid-detail/topid-detail.component';

@Component({
  selector: 'app-topic-content',
  templateUrl: './topic-content.component.html',
  styleUrls: ['./topic-content.component.css']
})
export class TopicContentComponent implements OnInit {
  @Input('topic') topic: Topic;
  @Input('semno') semno: Number;
  constructor(public dialog: MatDialog, public topicSv: TopicService, public authoSv: AuthService) { }

  ngOnInit() {
  }

  apply() {
    this.topicSv.applyToTopic(this.topic.idTop).subscribe(data => {
      if (data === 'CREATED') {
        this.topicSv.getAppliedTopic(this.semno).subscribe(topic => {
          this.topicSv.appliedTopic = topic;
        });
      }
    });
  }
  reject() {
    this.topicSv.reject(this.topicSv.appliedTopic.idTop).subscribe(data => {
      if (data === 'CREATED') {
        this.topicSv.appliedTopic = null;
      }
    });
  }

  getTopicId(): void {
    this.topicSv.getTopicDetail(this.topic.idTop).subscribe(rs => {
      console.log(rs);

      const dialogRef = this.dialog.open(TopidDetailComponent, {
        width: '450px',
        data: { topicDetail: rs }
      });
    });

  }

}
