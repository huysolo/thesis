import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { TaskComponent } from './component/task/task.component';
import { LayoutComponent } from './component/layout/layout.component';
import { PostComponent } from './component/post/post.component';


@NgModule({
  declarations: [
    AppComponent,
    TaskComponent,
    LayoutComponent,
    PostComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
