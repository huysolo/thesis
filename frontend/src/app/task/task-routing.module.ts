import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import {TaskContentComponent} from './components/task-content/task-content.component';
import { FormUploadComponent } from './components/upload/form-upload/form-upload.component';

const taskRoutes: Routes = [
  {
    path: 'content',
    component: TaskContentComponent,
    pathMatch: 'full'
  },
  {
    path: 'upload',
    component: FormUploadComponent,
    pathMatch: 'full'
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
