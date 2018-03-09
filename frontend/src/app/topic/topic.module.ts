import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopicContentComponent } from './components/topic-content/topic-content.component';
import { TopicListComponent } from './components/topic-list/topic-list.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [TopicContentComponent, TopicListComponent]
})
export class TopicModule { }
