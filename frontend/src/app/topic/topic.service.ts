import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { Topic } from '../models/Topic';
import { TopicDetail } from '../models/TopicDetail';

@Injectable()
export class TopicService {
  constructor(private http: HttpClient) {
  }
  private topicListUrl = 'http://localhost:8080/topic/listTopic';
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

  /**
   * getListTopicBySemesterAndProf
   */
  public getListTopicBySemesterAndProf(sem: number, profId: number): Observable<Topic[]>  {
    if (sem == -1 && profId == -1) {
      return this.getListTopic();
    } else {
      if (sem != -1 && profId != -1) {
        return this.http.get<Topic[]>(this.topicListUrl + '?semno=' + sem + '&profId=' + profId);
      } else {
        if (sem !== -1) {
          return this.http.get<Topic[]>(this.topicListUrl + '?semno=' + sem);
        } else {
          return this.http.get<Topic[]>(this.topicListUrl + '?profId=' + profId);
        }
      }
    }
  }

}
