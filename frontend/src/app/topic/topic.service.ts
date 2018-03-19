import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { Topic } from '../models/Topic';
import { TopicDetail } from '../models/TopicDetail';

@Injectable()
export class TopicService {
  constructor(private http: HttpClient) {
  }
  private topicListUrl = 'http://localhost:8080/topic/listTopic?semno=171';
  private topicDetailUrl = 'http://localhost:8080/topic/topicDetail';

  /**
   * getListTopic
   * Get List Topic for Current Semester
   */
  public getListTopic(): Observable<Topic[]> {
    return this.http.get<Topic[]>(this.topicListUrl);
  }

  /**
   * getTopicDetail
   */
  public getTopicDetail(id: number) {
    return this.http.get<TopicDetail>(this.topicDetailUrl);
  }

}
