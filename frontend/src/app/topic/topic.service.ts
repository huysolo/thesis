import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { Topic } from '../models/Topic';
import { TopicDetail } from '../models/TopicDetail';
import { AuthService } from '../core/auth.service';
import { Observer } from 'rxjs/Observer';

@Injectable()
export class TopicService {
  public pageList: Number[] = [];
  public selectedPage = 1;
  public pageSize = 5;
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
  private topicRejectUrl = 'http://localhost:8080/topic/reject';
  private topicListDraftUrl = 'http://localhost:8080/topic/listDraft';
  private topicPublishtUrl = 'http://localhost:8080/topic/publish';


  /**
   * reject
   */
  public reject(topicId: Number) {
    return this.http.post<any>(this.topicRejectUrl, topicId).map(res => {
      return res;
    });
  }
  /**
   * getAppliedTopic
   */
  public getAppliedTopic(semno: Number): Observable<Topic> {
    return this.http.get<Topic>(this.topicAppliedUrl +
      (semno != null ? '?semno=' + semno : ''));
  }
  /**
   * getListTopic
   * Get List Topic for Current Semester
   */
  public getListTopic(params: HttpParams): Observable<Topic[]> {
    return this.http.get<Topic[]>(this.topicRecentListUrl, {params: params});
  }

  /**
   * getTopicDetail
   */
  public getTopicDetail(id: Number): Observable<TopicDetail> {
    return this.http.get<TopicDetail>(this.topicDetailUrl + '?topid=' + id);
  }

  /**
   * getListTopicBySemesterAndProf
  */
  public getListTopicBySemesterAndProf(params: HttpParams): Observable<Topic[]>  {
    if (this.requestType === 'recent') {
      return this.getListTopic(params).map(data => {
        this.setPage(data.length);
        return data;
      });
    } else if (this.requestType === 'history') {
      return this.http.get<Topic[]>(this.topicListUrl, {params: params}
        //  + '?semno=' + sem + '&profId=' + profId + '', {params: this.params}
        ).map(data => {
        this.setPage(data.length);
        return data;
      });
    } else {
      return this.http.get<Topic[]>(this.topicListDraftUrl).map(data => {
        this.setPage(data.length);
        return data;
      });
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

  /**
   * publish
   */
  public publishTopic(topicId: Number) {
    return this.http.post<any>(this.topicPublishtUrl, topicId).map(res => {
      return res;
    });
  }

  public setPage(length: number) {
    const pageLength = length / this.pageSize + (length % this.pageSize > 0 ? 1 : 0);
    this.pageList = new Array<Number>();
    this.selectedPage = 1;
    for (let i = 1; i <= pageLength; i++) {
      this.pageList.push(i);
    }
  }
}
