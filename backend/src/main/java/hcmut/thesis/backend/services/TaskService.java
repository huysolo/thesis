/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services;

import hcmut.thesis.backend.modelview.StudentDoTask;
import hcmut.thesis.backend.modelview.TaskInfo;
import java.util.List;

/**
 *
 * @author MinBui
 */
public interface TaskService {
    List<StudentDoTask> getStudentDoTaskFromTaskID(int taskID);
    List<TaskInfo> getListTaskFromIDTopic(int topicID);
    List<StudentDoTask> getAllStudentDoTaskFromTopicID(int topicID);
    void createTask(TaskInfo taskInfo);
}
