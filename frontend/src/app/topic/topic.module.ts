import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopicContentComponent } from './components/topic-content/topic-content.component';
import { TopicListComponent } from './components/topic-list/topic-list.component';
import { ManageTopicComponent } from './pages/manage-topic/manage-topic.component';
import { TopicRoutingModule } from './topic-routing.module';
import { CreateTopicComponent } from './components/create-topic/create-topic.component';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from '../core/material.module';
import { TopidDetailComponent } from './components/topid-detail/topid-detail.component';

@NgModule({
  imports: [
    CommonModule,
    TopicRoutingModule,
    FormsModule,
    MaterialModule,
  ],
  declarations: [
    TopicContentComponent,
    TopicListComponent,
    ManageTopicComponent,
    CreateTopicComponent,
    TopidDetailComponent
  ],
})
export class TopicModule { }
