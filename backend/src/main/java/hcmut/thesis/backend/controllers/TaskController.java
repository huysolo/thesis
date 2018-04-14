/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.models.StudentTopicSem;
import hcmut.thesis.backend.models.Task;
import hcmut.thesis.backend.modelview.StudentDoTask;
import hcmut.thesis.backend.modelview.TaskInfo;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.repositories.StudentTaskRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.TaskRepo;
import hcmut.thesis.backend.services.ITaskDAO;
import hcmut.thesis.backend.services.TaskService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MinBui
 */
@Controller
@CrossOrigin
public class TaskController {

    @Autowired
    ITaskDAO itaskDAO;

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    TaskService taskService;

    @Autowired
    StudentTaskRepo stdTaskRepo;

    @Autowired
    StudentTopicSemRepo stdTopicSemRepo;

    @Autowired
    UserSession userSession;

    @RequestMapping(value = "/crttask", method = RequestMethod.POST)
    @ResponseBody
    public TaskInfo createTask(@RequestBody TaskInfo createInfo) {
        itaskDAO.createTask(createInfo);
        return createInfo;
    }

    @RequestMapping(value = "/getlisttask", method = RequestMethod.GET)
    @ResponseBody
    public List<TaskInfo> getListTask(@RequestParam("topicID") Integer topicID) {
        if (userSession.isProf() == true) {
            return taskService.getListTaskFromProf(topicID);
        } else {
            return taskService.getListTaskFromIDTopic(topicID);
        }
    }
    
    @RequestMapping(value = "/getlisttasktest", method = RequestMethod.GET)
    @ResponseBody
    public List<TaskInfo> getListTaskTest(@RequestParam("topicID") Integer topicID) {
        return taskService.getListTaskFromProf(topicID);
    }

    @RequestMapping(value = "/getallstd", method = RequestMethod.POST)
    @ResponseBody
    public List<StudentDoTask> getAllStudentDoTopic() {
        return taskService.getAllStudentDoTaskFromTopicID(1);
    }
    
    @RequestMapping(value = "/submittask")
    @ResponseBody
    public Task submitTask(@RequestParam("taskID") Integer taskID, 
                                    @RequestParam("submit") Integer submit) {
         return taskService.updateTaskSubmit(taskID, submit);
    }
    
    @RequestMapping(value = "/reviewtask")
    @ResponseBody
    public Task reviewTask(@RequestParam("taskID") Integer taskID, 
                                    @RequestParam("pass") Integer pass) {
         return taskService.updateTaskPass(taskID, pass);
    }
    
    

    @RequestMapping(value = "/tasktest")
    @ResponseBody
    public int createTasktest(@RequestParam("stdID") Integer stdID) {
         return stdTopicSemRepo.getTeamLeadFromStudentID(stdID);
    }
    
    
}
