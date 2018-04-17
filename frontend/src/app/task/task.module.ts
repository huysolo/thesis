import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TaskContentComponent } from './components/task-content/task-content.component';
import { TaskListComponent } from './components/task-list/task-list.component';
import {TaskRoutingModule} from './task-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {TaskService} from './task.service';
import {FilterPipe} from './components/task-content/filter.pipe';

@NgModule({
  imports: [
    CommonModule,
    TaskRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [TaskContentComponent, TaskListComponent, FilterPipe],
  providers: [TaskService]
})
export class TaskModule { }
