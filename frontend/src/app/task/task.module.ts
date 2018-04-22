import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TaskContentComponent } from './components/task-content/task-content.component';
import { TaskListComponent } from './components/task-list/task-list.component';
import {TaskRoutingModule} from './task-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {TaskService} from './task.service';
import {FilterPipe} from './components/task-content/filter.pipe';
import { DetailUploadComponent } from './components/upload/detail-upload/detail-upload.component';
import { FormUploadComponent } from './components/upload/form-upload/form-upload.component';
import { ListUploadComponent } from './components/upload/list-upload/list-upload.component';
import { UploadFileService } from './upload-file.service';
import { TaskChatgroupComponent } from './components/task-chatgroup/task-chatgroup.component';

@NgModule({
  imports: [
    CommonModule,
    TaskRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    TaskContentComponent,
    TaskListComponent,
    FilterPipe,
    DetailUploadComponent,
    FormUploadComponent,
    ListUploadComponent,
    TaskChatgroupComponent
  ],
  providers: [TaskService, UploadFileService]
})
export class TaskModule { }
