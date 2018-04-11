import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import {TaskContentComponent} from './components/task-content/task-content.component';
import {TaskCreateComponent} from './components/task-create/task-create.component';

const taskRoutes: Routes = [
  {
    path: 'content',
    component: TaskContentComponent
  },
  {
    path: 'create',
    component: TaskCreateComponent
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
