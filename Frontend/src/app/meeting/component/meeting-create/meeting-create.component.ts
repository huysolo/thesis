import { Component, OnInit } from '@angular/core';
import {Meeting} from '../../meeting';
import { TimeLocation } from '../../time-location';

@Component({
  selector: 'app-meeting-create',
  templateUrl: './meeting-create.component.html',
  styleUrls: ['./meeting-create.component.css']
})
export class MeetingCreateComponent implements OnInit {
  temp: Array<any>;
  meetingCreate = new Meeting();

  constructor() { 
    this.newMeeting();
  }

  ngOnInit() {
  }

  newMeeting(){
    const temp = new TimeLocation();
    this.meetingCreate.timelocation.push(temp);
  }

  removeMeeting(i: number) {
    this.meetingCreate.timelocation.splice(i, 1);
  }

  addTimeLocation(){
    const temp = new TimeLocation();
    this.meetingCreate.timelocation.push(temp);
  }

  createMeeting(){
    console.log(this.meetingCreate);
  }

}
