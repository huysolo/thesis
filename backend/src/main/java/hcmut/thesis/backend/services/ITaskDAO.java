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
public interface ITaskDAO {
    TaskInfo createTask(TaskInfo taskInfo, int topicid);
    void createStudentTask(int taskID, List<StudentDoTask> std);
    List<StudentDoTask> getStudentDoTask (int topicID);
}
