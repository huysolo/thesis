import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ManageTopicComponent } from './pages/manage-topic/manage-topic.component';
import { TopicListComponent } from './components/topic-list/topic-list.component';
import { TopicDetail } from '../models/TopicDetail';

const topicRoutes: Routes = [
  {
    path: '',
    component: ManageTopicComponent,
    children: [
      {
        path: ':typ',
        component: TopicListComponent
      }
    ]
  }
];
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(topicRoutes),
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})

export class TopicRoutingModule { }
