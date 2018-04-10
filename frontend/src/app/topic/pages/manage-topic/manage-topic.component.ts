import { Component, OnInit } from '@angular/core';
import { TopicService } from '../../topic.service';
import { Topic } from '../../../models/Topic';
import { Observable } from 'rxjs/Observable';
import { CommonService } from '../../../core/common.service';
import { Semester } from '../../../models/Semester';
import { ProfInfo } from '../../../models/ProfInfo';
import { AuthService } from '../../../core/auth.service';

@Component({
  selector: 'app-manage-topic',
  templateUrl: './manage-topic.component.html',
  styleUrls: ['./manage-topic.component.css']
})
export class ManageTopicComponent implements OnInit {
  public listSem: Observable<Semester[]>;
  public topicLst: Observable<Topic[]>;
  public profLst: Observable<ProfInfo[]>;

  public selectedSem;
  public selectedProfId;
  constructor(public topicSv: TopicService, public commonSv: CommonService, public authoSv: AuthService) { }

  ngOnInit() {
    this.selectedProfId = this.authoSv.isProfessor() ? this.authoSv.getProfID() : -1;
    this.selectedSem = this.authoSv.isProfessor() ? -1 : -1;
    this.topicLstInterval();
    this.listSem = this.commonSv.getListSemester();
    this.profLst = this.commonSv.getListProf();
  }

  topicLstInterval(): void {
    // setInterval(() => {
      this.topicLst = this.topicSv.getListTopic();
    // }, 300000);
  }

  onChangeSemester(sem) {
    this.selectedSem = sem;
    this.topicLst = this.topicSv.getListTopicBySemesterAndProf(this.selectedSem, this.selectedProfId);
  }

  onChangeProf(prof) {
    this.selectedProfId = prof;
    this.topicLst = this.topicSv.getListTopicBySemesterAndProf(this.selectedSem, this.selectedProfId);
  }



}
