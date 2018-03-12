import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes =[
  {
    path: 'topic',
    loadChildren: 'app/topic/topic.module#TopicModule'
  },
  {
    path: 'user',
    loadChildren: 'app/user/user.module#UserModule'
  }
]

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
