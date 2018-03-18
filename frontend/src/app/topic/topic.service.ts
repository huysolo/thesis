import { HttpClient } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Topic } from '../models/Topic';

@Injectable()
export class TopicService {
  constructor(private http: HttpClient) {
  }
  private topicListUrl = 'http://localhost:8080/topic/listTopic';

  /**
   * getListTopic
   * Get List Topic
   */
  public getListTopic(): Observable<Topic[]> {
    return this.http.get<Topic[]>(this.topicListUrl);
  }

}
