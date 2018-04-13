import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import {TaskContentComponent} from './components/task-content/task-content.component';

const taskRoutes: Routes = [
  {
    path: 'content',
    component: TaskContentComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(taskRoutes)
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class TaskRoutingModule { }
