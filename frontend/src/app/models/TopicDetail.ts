import { Topic } from './Topic';
import { TopicMission } from './TopicMission';
import { TopicRequirement } from './TopicRequirement';

export class TopicDetail {
  topic: Topic;
  topicMission: TopicMission[];
  topicRequirement: TopicRequirement[];
  constructor() {
    this.topic = new Topic();
    this.topicMission = new Array<TopicMission>();
    this.topicRequirement = new Array<TopicRequirement>();
  }

}
