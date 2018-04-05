import { Component, OnInit, Input } from '@angular/core';
import { TopicDetail } from '../../../models/TopicDetail';
import { Topic } from '../../../models/Topic';
import { TopicRequirement } from '../../../models/TopicRequirement';
import { TopicMission } from '../../../models/TopicMission';
import { AuthService } from '../../../core/auth.service';

@Component({
  selector: 'app-create-topic',
  templateUrl: './create-topic.component.html',
  styleUrls: ['./create-topic.component.css']
})
export class CreateTopicComponent implements OnInit {
  constructor(public authoSv: AuthService) { }
  public lst: string[] = ['haha', 'huhu'];
  createTopic: TopicDetail;
  ngOnInit() {
    this.createTopic = new TopicDetail();
    this.createTopic.topic.idProf = this.authoSv.getProfID();
    this.createTopic.topicMission.push(new TopicMission());
    this.createTopic.topicRequirement.push(new TopicRequirement());
  }

  submitTopic() {
    console.log(this.createTopic);
  }

  addReq() {
    this.createTopic.topicRequirement.push(new TopicRequirement());
  }

  removeReq(removePos: number) {
    this.createTopic.topicRequirement.splice(removePos, 1);
  }

  addMission() {
    this.createTopic.topicMission.push(new TopicMission());
  }

  removeMission(removePos: number) {
    this.createTopic.topicMission.splice(removePos, 1);
  }

}
