import { Component, OnInit, Input } from '@angular/core';
import { TopicDetail } from '../../../models/TopicDetail';
import { Topic } from '../../../models/Topic';
import { TopicRequirement } from '../../../models/TopicRequirement';
import { TopicMission } from '../../../models/TopicMission';
import { AuthService } from '../../../core/auth.service';
import { TopicService } from '../../topic.service';

@Component({
  selector: 'app-create-topic',
  templateUrl: './create-topic.component.html',
  styleUrls: ['./create-topic.component.css']
})
export class CreateTopicComponent implements OnInit {
  constructor(public authoSv: AuthService, public topicSv: TopicService) { }
  createTopic: TopicDetail;
  ngOnInit() {
    this.createTopic = new TopicDetail();
    this.createTopic.topic.idProf = this.authoSv.getProfID();
    this.createTopic.topicMission.push(new TopicMission(0));
    this.createTopic.topicRequirement.push(new TopicRequirement(0));
  }

  submitTopic() {
    this.topicSv.createTopic(this.createTopic).subscribe(data => {
      console.log(data);
    });
  }

  addReq() {
    const req = new TopicRequirement(0);
    this.createTopic.topicRequirement.push(new TopicRequirement(0));
  }

  removeReq(removePos: number) {
    this.createTopic.topicRequirement.splice(removePos, 1);
  }

  addMission() {
    const mission = new TopicMission(0);
    mission.idTopic = 0;
    this.createTopic.topicMission.push(new TopicMission(0));
  }

  removeMission(removePos: number) {
    this.createTopic.topicMission.splice(removePos, 1);
  }

}
