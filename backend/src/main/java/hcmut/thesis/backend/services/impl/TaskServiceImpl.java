/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.StudentTask;
import hcmut.thesis.backend.models.StudentTopicSem;
import hcmut.thesis.backend.models.Task;
import hcmut.thesis.backend.modelview.PageInfo;
import hcmut.thesis.backend.modelview.StudentDoTask;
import hcmut.thesis.backend.modelview.TaskInfo;
import hcmut.thesis.backend.repositories.StudentTaskRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.TaskRepo;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.services.TaskService;
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
    
    @Override
    public List<StudentDoTask> getStudentDoTaskFromTaskID(int taskID){
        List<StudentDoTask> listStd = new ArrayList<>();
        List<StudentTask> t = stdTaskRepo.getStudentDoTaskFromIDTask(taskID);
        for(int i = 0; i< t.size(); i++){
            StudentDoTask temp = new StudentDoTask();
            temp.setStdName(userRepo.getUserNameFromID(t.get(i).getIdStudent()));
            temp.setArchive(t.get(i).getArchive());
            temp.setUploadDate(t.get(i).getUploadDate());
            listStd.add(temp);
        }
       return listStd;
    }
    
    @Override
    public List<TaskInfo> getListTaskFromIDTopic(int topicID){
        List<TaskInfo> listTask = new ArrayList<>();
        List<Task> t = taskRepo.getTaskFromIDTopic(topicID);
        for(int i = 0; i< t.size(); i++){
            
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
    public List<StudentDoTask> getAllStudentDoTaskFromTopicID(int topicID){
        List<StudentDoTask> listStd = new ArrayList<>();
        List<StudentTopicSem> t = stdTopicSemRepo.getAllStudentByIdTopicSem(topicID);
        for(int i = 0; i< t.size(); i++){
            StudentDoTask temp = new StudentDoTask();
            temp.setStdName(userRepo.getUserNameFromID(t.get(i).getIdStudent()));
            listStd.add(temp);
        }
       return listStd;
    }
    
    @Override
    public List<TaskInfo> getListTaskFromProf(int topicID){
        List<TaskInfo> listTask = new ArrayList<>();
        List<Task> t = taskRepo.getTaskSubmitFromProf(topicID);
        for(int i = 0; i< t.size(); i++){
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
    public PageInfo getPage(int pageNumber, int topicID, Boolean isStd){
        PageInfo page = new PageInfo();
        List<TaskInfo> listTask = new ArrayList<>();       
        List<Task> t = (isStd == true)? taskRepo.getTaskFromIDTopic(topicID): taskRepo.getTaskSubmitFromProf(topicID);
        for(int i = 4*pageNumber; i< min(4*(pageNumber + 1),t.size()); i++){
            
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
        page.setPageCount((t.size() / 4) + 1);
        page.setTaskList(listTask);
        return page;
    }
}
