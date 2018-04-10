import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { Topic } from '../models/Topic';
import { TopicDetail } from '../models/TopicDetail';
import { AuthService } from '../core/auth.service';

@Injectable()
export class TopicService {
  constructor(private http: HttpClient, private authoSv: AuthService) {
  }
  private topicListUrl = 'http://localhost:8080/topic/listTopic';
  private topicDetailUrl = 'http://localhost:8080/topic/topicDetail';
  private topicCreatelUrl = 'http://localhost:8080/topic/create';
  private topicListSizeUrl = 'http://localhost:8080/topic/listTopicSize';
  private seletedPage: number;
  private pageSize: number;
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
    return this.http.get<Topic[]>(this.topicListUrl + '?semno=' + sem + '&profId=' + profId + '');
  }

  public createTopic(topicDetail: TopicDetail) {
    return this.http.post<any>(this.topicCreatelUrl, topicDetail)
      .map(res => {
      });
  }

}
