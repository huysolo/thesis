import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TaskContentComponent } from './components/task-content/task-content.component';
import { TaskListComponent } from './components/task-list/task-list.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [TaskContentComponent, TaskListComponent]
})
export class TaskModule { }
