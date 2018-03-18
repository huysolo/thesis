import { Component, OnInit, Input } from '@angular/core';
import { Topic } from '../../../models/Topic';

@Component({
  selector: 'app-topic-content',
  templateUrl: './topic-content.component.html',
  styleUrls: ['./topic-content.component.css']
})
export class TopicContentComponent implements OnInit {
  @Input('topic') topic: Topic;
  constructor() { }

  ngOnInit() {
  }

}
