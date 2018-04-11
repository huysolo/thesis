import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { MainPageComponent } from '../main-page/main-page.component';
import {LoginComponent} from '../user/pages/login/login.component';
import {MeetingComponent} from '../meeting/meeting.component';

const appRoutes: Routes = [
  {
    path: 'topic',
    loadChildren: 'app/topic/topic.module#TopicModule'
  },
  {
    path: 'user',
    loadChildren: 'app/user/user.module#UserModule'
  },
  {
    path: 'task',
    loadChildren: 'app/task/task.module#TaskModule'
  },
  {
    path: 'meeting',
    component: MeetingComponent
  }
  ,
  {
    path: '',
    component: MainPageComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
