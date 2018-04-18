import { Component, OnInit, NgZone } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Topic } from '../../../models/Topic';
import { Semester } from '../../../models/Semester';
import { TopicService } from '../../topic.service';
import { CommonService } from '../../../core/common.service';
import { ProfInfo } from '../../../models/ProfInfo';
import { AuthService } from '../../../core/auth.service';
import { ActivatedRoute } from '@angular/router';
import { Observer } from 'rxjs/Observer';
import { TopicDetail } from '../../../models/TopicDetail';
import { TopicMission } from '../../../models/TopicMission';
import { TopicRequirement } from '../../../models/TopicRequirement';
import { HttpParams } from '@angular/common/http';
import { Specialize } from '../../../models/Specialize';
declare var $: any;
@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.css']
})
export class TopicListComponent implements OnInit {
  public listSem: Observable<Semester[]>;
  public profLst: Observable<ProfInfo[]>;
  public specLst: Observable<Specialize[]>;

  public selectedSem = null;
  public selectedProfId = null;
  public selectedSpecId = null;

  public topicDetail = new TopicDetail();
  title: String = 'New';
  p = 1;

  constructor(public topicSv: TopicService, private zone: NgZone,
    public commonSv: CommonService, public authoSv: AuthService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.selectedProfId = this.authoSv.isProfessor() ? this.authoSv.getProfID() : null;
    this.route.params.subscribe(params => {
      // this.selectedSem = -1;
      this.listSem = this.commonSv.getListSemester();
      this.profLst = this.commonSv.getListProf();
      this.specLst = this.commonSv.getListSpec();
      this.topicSv.requestType = params['typ'];
      this.topicSv.appliedTopic = null;
      if (this.topicSv.requestType === 'recent') {
        this.getAppliedTopic();
      }
      this.getWithRarams();
      // this.topicSv.topicLst = this.topicSv.getListTopicBySemesterAndProf(this.selectedSem, this.selectedProfId);
    });
  }

  getWithRarams() {
    this.zone.run(() => {
      let params = new HttpParams();
      if (this.selectedSem != null) {
        params = params.set('semno', this.selectedSem);
      }
      if (this.selectedProfId != null) {
        params = params.set('profId', this.selectedProfId);
      }
      if (this.selectedSpecId != null) {
        params = params.set('spec', this.selectedSpecId);
      }

      this.topicSv.topicLst = this.topicSv.getListTopicBySemesterAndProf(params);
    });

  }

  onChangeSemester(sem) {
    this.selectedSem = sem;
    this.getWithRarams();
    if (this.selectedSem !== null) {
      this.getAppliedTopic();
    }
  }

  onChangeProf(prof) {
    this.selectedProfId = prof;
    this.getWithRarams();
    // this.topicSv.topicLst = this.topicSv.getListTopicBySemesterAndProf(this.selectedSem, this.selectedProfId);
  }

  onChangeSpec(spec) {
    this.selectedSpecId = spec;
    this.getWithRarams();
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

  onEdit(event) {
    this.topicSv.getTopicDetail(event).subscribe(data => {
      this.title = 'Edit';
      this.topicDetail = data;
      $('#createTopic').modal('show');
    });
  }

  onApply(event: Topic) {
    this.topicSv.topicLst = this.topicSv.topicLst.map(topicLst => {
      this.topicSv.appliedTopic = event;
      return topicLst.filter(top => {
        return top != null && top.idTop !==  this.topicSv.appliedTopic.idTop;
      });
    });
  }

  newTopic() {
    this.title = 'New';
    this.topicDetail = new TopicDetail();
    this.topicDetail.topic.idProf = this.authoSv.getProfID();
    this.topicDetail.topicMission.push(new TopicMission(0));
    this.topicDetail.topicRequirement.push(new TopicRequirement(0));
  }
}
