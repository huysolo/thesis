export class TaskInfo {
  title: String;
  description: String;
  deadline: String;
  student: String[];
  public TaskInfo(title: String, des: String, deadline: String, student: String[]) {
    this.title = title;
    this.description = des;
    this.deadline = deadline;
    this.student = student;
  }
}
