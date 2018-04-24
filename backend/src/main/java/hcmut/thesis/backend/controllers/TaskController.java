/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.models.Task;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.modelview.ChatGroupInfo;
import hcmut.thesis.backend.modelview.PageInfo;
import hcmut.thesis.backend.modelview.StudentDoTask;
import hcmut.thesis.backend.modelview.TaskComment;
import hcmut.thesis.backend.modelview.TaskInfo;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.repositories.ProfessorRepo;
import hcmut.thesis.backend.repositories.SemesterRepo;
import hcmut.thesis.backend.repositories.StudentRepo;
import hcmut.thesis.backend.repositories.StudentTaskRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.TaskRepo;
import hcmut.thesis.backend.repositories.TopicRepo;
import hcmut.thesis.backend.services.ChatGroupService;
import hcmut.thesis.backend.services.ITaskDAO;
import hcmut.thesis.backend.services.IUserDAO;
import hcmut.thesis.backend.services.TaskService;
import hcmut.thesis.backend.services.TopicService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    IUserDAO iuserDAO;

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

    @Autowired
    TopicRepo topicRepo;

    @Autowired
    ProfessorRepo profRepo;

    @Autowired
    StudentRepo stdRepo;
    
    @Autowired
    ChatGroupService chatGroupService;
    @Autowired
    SemesterRepo semRepo;
    
    @Autowired
    TopicService topicService;

    @RequestMapping(value = "/crttask", method = RequestMethod.POST)
    @ResponseBody
    public TaskInfo createTask(@RequestBody TaskInfo createInfo) {
        int topicid = taskService.getCurrTopicFromStdID(userSession.getStudent().getIdStudent()).getIdTop();
        return itaskDAO.createTask(createInfo, topicid);
    }

    @RequestMapping(value = "/getlisttask", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo getListTask(@RequestParam("topicID") Integer topicID,
            @RequestParam("page") Integer pageNumber) {
        if (topicID == -1) {
            topicID = topicService.getAppliedTopic(semRepo.getCurrentApplySemester().get(0),userSession.getStudent().getIdStudent()).getIdTop();
        }
        if (userSession.isStudent() == true) {
            return taskService.getPage(pageNumber,topicID, true);
        } else {
            return taskService.getPage(pageNumber,topicID, false);
        }
    }

    @RequestMapping(value = "/topiccount", method = RequestMethod.GET)
    @ResponseBody
    public List<Topic> getListTaskTest() {
        return topicRepo.findTopicFromProfID(profRepo.getProfessorByIdUser(userSession.getUserID()));
    }
    
    @RequestMapping(value = "/getlisttopic", method = RequestMethod.GET)
    @ResponseBody
    public List<Topic> getTopicFromSemID(@RequestParam(value="semid") Integer semid) {
        if(semid == -1){
            semid = semRepo.getCurrentApplySemester().get(0);
        }
        return topicRepo.findListSemFromSemID(userSession.getProf().getIdProfessor(), semid);
    }
    
    @RequestMapping(value = "/stdgetlisttopic", method = RequestMethod.GET)
    @ResponseBody
    public List<Topic> getTopicFromStd() {
       return taskService.getListTopicFromStdID(userSession.getStudent().getIdStudent());
    }
    
    @RequestMapping(value = "/semcount", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getListSem() {
        return topicRepo.findListSemFromProfID(userSession.getProf().getIdProfessor());
    }

    @RequestMapping(value = "/getallstd", method = RequestMethod.GET)
    @ResponseBody
    public List<StudentDoTask> getAllStudentDoTopic() {
        int topicid = taskService.getCurrTopicFromStdID(userSession.getStudent().getIdStudent()).getIdTop();
        return taskService.getAllStudentDoTaskFromTopicID(topicid);
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
    
    @RequestMapping(value = "/getallmessage")
    @ResponseBody
    public List<ChatGroupInfo> getAllMessage(){
        return chatGroupService.getchatGroupFromUderID(userSession.getUserID());
    }
    
    @RequestMapping(value = "/gettaskcomment")
    @ResponseBody
    public List<TaskComment> getTaskComment(@RequestParam("taskid") Integer taskID){
        return taskService.getTaskComment(taskID);
    }

    @RequestMapping(value = "/tasktest")
    @ResponseBody
    public int createTasktest(@RequestParam("stdid") Integer stdid, 
            @RequestParam("semid") Integer semid){
        return 0;
    }

}
