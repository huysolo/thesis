import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { Topic } from '../models/Topic';
import { TopicDetail } from '../models/TopicDetail';
import { AuthService } from '../core/auth.service';

@Injectable()
export class TopicService {
  requestType: string;
  constructor(private http: HttpClient, private authoSv: AuthService) {
    this.requestType = 'recent';
  }
  appliedTopic: Topic;
  topicLst: Observable<Topic[]>;
  private topicListUrl = 'http://localhost:8080/topic/listTopic';
  private topicRecentListUrl = 'http://localhost:8080/topic/recentTopics';
  private topicDetailUrl = 'http://localhost:8080/topic/topicDetail';
  private topicCreatelUrl = 'http://localhost:8080/topic/create';
  private topicListSizeUrl = 'http://localhost:8080/topic/listTopicSize';
  private topicApplyUrl = 'http://localhost:8080/topic/apply';
  private topicAppliedUrl = 'http://localhost:8080/topic/appliedTopic';
  private seletedPage: number;
  private pageSize: number;

  /**
   * getAppliedTopic
   */
  public getAppliedTopic(semno: Number): Observable<Topic> {
    return this.http.get<Topic>(this.topicAppliedUrl +
      (semno !== -1 ? '?semno=' + semno : ''));
  }
  /**
   * getListTopic
   * Get List Topic for Current Semester
   */
  public getListTopic(profId: Number): Observable<Topic[]> {
    return this.http.get<Topic[]>(this.topicRecentListUrl  + '?profId='  + profId);
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
    if (this.requestType === 'recent') {
      return this.getListTopic(profId);
    } else if (this.requestType === 'history') {
      return this.http.get<Topic[]>(this.topicListUrl + '?semno=' + sem + '&profId=' + profId + '');
    }
  }

  public createTopic(topicDetail: TopicDetail) {
    return this.http.post<any>(this.topicCreatelUrl, topicDetail)
      .map(res => {
        return res;
      });
  }

  /**
   * applyToTopic
   */
  public applyToTopic(topicId: Number) {
    return this.http.post<any>(this.topicApplyUrl, topicId).map(res => {
      return res;
    });
  }


}
