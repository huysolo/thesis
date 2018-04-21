import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AuthService } from './auth.service';
import { TopicService } from '../topic/topic.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { CommonService } from './common.service';
import { AuthGuardUserService } from './auth-guard-user.service';
import {TaskService} from '../task/task.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  declarations: [],
  providers: [
    AuthService,
    TopicService,
    CommonService,
    AuthGuardUserService,
    TaskService
  ]
})
export class CoreModule { }
