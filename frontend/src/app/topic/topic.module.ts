import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopicContentComponent } from './components/topic-content/topic-content.component';
import { TopicListComponent } from './components/topic-list/topic-list.component';
import { ManageTopicComponent } from './pages/manage-topic/manage-topic.component';
import { TopicRoutingModule } from './topic-routing.module';
import { CreateTopicComponent } from './components/create-topic/create-topic.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    TopicRoutingModule,
    FormsModule,
    TopicRoutingModule
  ],
  declarations: [
    TopicContentComponent,
    TopicListComponent,
    ManageTopicComponent,
    CreateTopicComponent
  ],
})
export class TopicModule { }
