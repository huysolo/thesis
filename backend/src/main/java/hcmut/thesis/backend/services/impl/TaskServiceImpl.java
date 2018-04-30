/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.CommentTask;
import hcmut.thesis.backend.models.StudentTask;
import hcmut.thesis.backend.models.StudentTopicSem;
import hcmut.thesis.backend.models.Task;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.models.User;
import hcmut.thesis.backend.modelview.PageInfo;
import hcmut.thesis.backend.modelview.StudentDoTask;
import hcmut.thesis.backend.modelview.TaskComment;
import hcmut.thesis.backend.modelview.TaskInfo;
import hcmut.thesis.backend.repositories.SemesterRepo;
import hcmut.thesis.backend.repositories.StudentRepo;
import hcmut.thesis.backend.repositories.StudentTaskRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.TaskCommentRepo;
import hcmut.thesis.backend.repositories.TaskRepo;
import hcmut.thesis.backend.repositories.TopicRepo;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.services.CommonService;
import hcmut.thesis.backend.services.TaskService;
import hcmut.thesis.backend.services.TopicService;
import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinBui
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    StudentTaskRepo stdTaskRepo;

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    StudentTopicSemRepo stdTopicSemRepo;

    @Autowired
    TaskCommentRepo taskCommentRepo;

    @Autowired
    TopicService topicService;

    @Autowired
    TopicRepo topicRepo;

    @Autowired
    SemesterRepo semRepo;

    @Autowired
    CommonService commonService;
    
    @Autowired
    StudentRepo stdRepo;

    @Override
    public List<StudentDoTask> getStudentDoTaskFromTaskID(int taskID) {
        List<StudentDoTask> listStd = new ArrayList<>();
        List<StudentTask> t = stdTaskRepo.getStudentDoTaskFromIDTask(taskID);
        for (int i = 0; i < t.size(); i++) {
            StudentDoTask temp = new StudentDoTask();
            temp.setStdName(userRepo.getUserNameFromID(t.get(i).getIdStudent()));
            temp.setArchive(t.get(i).getArchive());
            temp.setUploadDate(t.get(i).getUploadDate());
            listStd.add(temp);
        }
        return listStd;
    }

    @Override
    public List<TaskInfo> getListTaskFromIDTopic(int topicID) {
        List<TaskInfo> listTask = new ArrayList<>();
        List<Task> t = taskRepo.getTaskFromIDTopic(topicID);
        for (int i = 0; i < t.size(); i++) {

            TaskInfo temp = new TaskInfo();
            temp.setTaskID(t.get(i).getIdTask());
            temp.setTitle(t.get(i).getTitle());
            temp.setDescription(t.get(i).getDescription());
            temp.setDeadline(t.get(i).getDeadline());
            temp.setSubmit(t.get(i).getSubmit());
            temp.setPass(t.get(i).getPass());
            temp.setStudent(getStudentDoTaskFromTaskID(t.get(i).getIdTask()));
            listTask.add(temp);
        }
        return listTask;
    }

    @Override
    public List<StudentDoTask> getAllStudentDoTaskFromTopicID(int topicID) {
        List<StudentDoTask> listStd = new ArrayList<>();
        List<StudentTopicSem> t = stdTopicSemRepo.getAllStudentByIdTopicSem(topicID);
        for (int i = 0; i < t.size(); i++) {
            StudentDoTask temp = new StudentDoTask();
            int userID = stdRepo.getUserIDFromStdID(t.get(i).getIdStudent());
            temp.setStdName(userRepo.getUserNameFromID(userID));
            listStd.add(temp);
        }
        return listStd;
    }

    @Override
    public List<TaskInfo> getListTaskFromProf(int topicID) {
        List<TaskInfo> listTask = new ArrayList<>();
        List<Task> t = taskRepo.getTaskSubmitFromProf(topicID);
        for (int i = 0; i < t.size(); i++) {
            TaskInfo temp = new TaskInfo();

            temp.setTitle(t.get(i).getTitle());
            temp.setTaskID(t.get(i).getIdTask());
            temp.setDescription(t.get(i).getDescription());
            temp.setDeadline(t.get(i).getDeadline());
            temp.setSubmit(t.get(i).getSubmit());
            temp.setPass(t.get(i).getPass());
            temp.setStudent(getStudentDoTaskFromTaskID(t.get(i).getIdTask()));
            listTask.add(temp);
        }
        return listTask;
    }

    @Override
    public Task updateTaskSubmit(int taskID, int submit) {
        Task temp = taskRepo.getTaskFromTaskID(taskID);
        temp.setSubmit(submit);
        taskRepo.save(temp);
        return temp;
    }

    @Override
    public Task updateTaskPass(int taskID, int pass) {
        Task temp = taskRepo.getTaskFromTaskID(taskID);
        temp.setPass(pass);
        taskRepo.save(temp);
        return temp;
    }

    @Override
    public PageInfo getPage(int pageNumber, int topicID, Boolean isStd) {
        PageInfo page = new PageInfo();
        List<TaskInfo> listTask = new ArrayList<>();
        List<Task> t = (isStd == true) ? taskRepo.getTaskFromIDTopic(topicID) : taskRepo.getTaskSubmitFromProf(topicID);
        for (int i = 8 * pageNumber; i < min(8 * (pageNumber + 1), t.size()); i++) {

            TaskInfo temp = new TaskInfo();
            temp.setTaskID(t.get(i).getIdTask());
            temp.setTitle(t.get(i).getTitle());
            temp.setDescription(t.get(i).getDescription());
            temp.setDeadline(t.get(i).getDeadline());
            temp.setSubmit(t.get(i).getSubmit());
            temp.setPass(t.get(i).getPass());
            temp.setStudent(getStudentDoTaskFromTaskID(t.get(i).getIdTask()));
            listTask.add(temp);
        }
        int count;
        if (t.size() % 8 == 0) {
            count = t.size() / 8;
        } else {
            count = t.size() / 8 + 1;
        }
        page.setPageCount(count);
        page.setTaskList(listTask);
        return page;
    }

    @Override
    public List<TaskComment> getTaskComment(int taskID) {
        List<TaskComment> listComment = new ArrayList<>();
        List<CommentTask> t = taskCommentRepo.getCommentFromTaskID(taskID);
        for (int i = 0; i < t.size(); i++) {
            TaskComment temp = new TaskComment();
            User user = userRepo.getUserFromID(t.get(i).getIdUser());
            temp.setUsername(user.getUserName());
            temp.setGender(user.getGender());
            temp.setTaskID(taskID);
            temp.setTime(t.get(i).getTime());
            temp.setContent(t.get(i).getContent());
            listComment.add(temp);
        }
        return listComment;
    }

    @Override
    public List<Topic> getListTopicFromStdID(int stdid) {
        List<Topic> listTopic = new ArrayList<>();
        List<StudentTopicSem> t = stdTopicSemRepo.getStudentTopicSemByIdStudent(stdid);
        for (int i = 0; i < t.size(); i++) {
            Topic temp = topicRepo.getTopicFromTopicID(t.get(i).getIdTopicSem());
            listTopic.add(temp);
        }
        return listTopic;
    }

    @Override
    public Topic getCurrTopicFromStdID(int stdid) {
        Integer currSem = commonService.getCurrentSem();
        if (currSem != null) {
            return topicService.getAppliedTopic(currSem, stdid);
        } else {
            return null;
        }
    }
}
