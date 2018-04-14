import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Topic } from '../../../models/Topic';
import { Semester } from '../../../models/Semester';
import { TopicService } from '../../topic.service';
import { CommonService } from '../../../core/common.service';
import { ProfInfo } from '../../../models/ProfInfo';
import { AuthService } from '../../../core/auth.service';
import { ActivatedRoute } from '@angular/router';
import { Observer } from 'rxjs/Observer';

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.css']
})
export class TopicListComponent implements OnInit {
  public listSem: Observable<Semester[]>;
  public profLst: Observable<ProfInfo[]>;

  public selectedSem;
  public selectedProfId;

  constructor(public topicSv: TopicService, public commonSv: CommonService, public authoSv: AuthService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.selectedProfId = this.authoSv.isProfessor() ? this.authoSv.getProfID() : -1;
    this.route.params.subscribe(params => {
      this.selectedSem = -1;
      this.listSem = this.commonSv.getListSemester();
      this.profLst = this.commonSv.getListProf();
      this.topicSv.requestType = params['typ'];
      this.topicSv.appliedTopic = null;
      if (this.topicSv.requestType === 'recent') {
        this.getAppliedTopic();
      }
      this.topicSv.topicLst = this.topicSv.getListTopicBySemesterAndProf(this.selectedSem, this.selectedProfId);
    });
  }

  onChangeSemester(sem) {
    this.selectedSem = sem;
    this.topicSv.topicLst = this.topicSv.getListTopicBySemesterAndProf(this.selectedSem, this.selectedProfId);
    if (this.selectedSem !== -1) {
      this.getAppliedTopic();
    }
  }

  onChangeProf(prof) {
    this.selectedProfId = prof;
    this.topicSv.topicLst = this.topicSv.getListTopicBySemesterAndProf(this.selectedSem, this.selectedProfId);
  }

  getAppliedTopic() {
    this.topicSv.getAppliedTopic(this.selectedSem).subscribe(topic => {
      this.topicSv.appliedTopic = topic;
    });
  }

  validTopic(topicId: number) {
    return this.topicSv.appliedTopic == null ||  topicId !== this.topicSv.appliedTopic.idTop;
  }

  setPage(selectedPage) {
    if (selectedPage > 0 && selectedPage <= this.topicSv.pageList.length) {
      this.topicSv.selectedPage = selectedPage;
    }
  }

  inSelectedList(pos: number) {
    return pos < this.topicSv.selectedPage * this.topicSv.pageSize && pos >= (this.topicSv.selectedPage - 1) * this.topicSv.pageSize;
  }
}
