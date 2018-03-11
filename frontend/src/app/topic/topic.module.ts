import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopicContentComponent } from './components/topic-content/topic-content.component';
import { TopicListComponent } from './components/topic-list/topic-list.component';
import { ManageTopicComponent } from './pages/manage-topic/manage-topic.component';
import { TopicRoutingModule } from './topic-routing.module';

@NgModule({
  imports: [
    CommonModule,
    TopicRoutingModule
  ],
  declarations: [TopicContentComponent, TopicListComponent, ManageTopicComponent], 
})
export class TopicModule { }
