import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ManageTopicComponent } from './pages/manage-topic/manage-topic.component';

const topicRoutes: Routes = [
  {
    path: '',
    component: ManageTopicComponent
  }
];
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(topicRoutes)
  ],
  declarations: [],
  exports:[
    RouterModule
  ]
})

export class TopicRoutingModule { }
