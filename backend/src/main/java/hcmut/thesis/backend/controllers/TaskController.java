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
import hcmut.thesis.backend.repositories.StudentRepo;
import hcmut.thesis.backend.repositories.StudentTaskRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.TaskRepo;
import hcmut.thesis.backend.repositories.TopicRepo;
import hcmut.thesis.backend.services.ChatGroupService;
import hcmut.thesis.backend.services.ITaskDAO;
import hcmut.thesis.backend.services.TaskService;
import hcmut.thesis.backend.services.impl.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    StorageService storageService;

    @Autowired
    TopicRepo topicRepo;

    @Autowired
    ProfessorRepo profRepo;

    @Autowired
    StudentRepo stdRepo;
    
    @Autowired
    ChatGroupService chatGroupService;

    @RequestMapping(value = "/crttask", method = RequestMethod.POST)
    @ResponseBody
    public TaskInfo createTask(@RequestBody TaskInfo createInfo) {
        itaskDAO.createTask(createInfo);
        return createInfo;
    }

    @RequestMapping(value = "/getlisttask", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo getListTask(@RequestParam("topicID") Integer topicID,
            @RequestParam("page") Integer pageNumber) {
        if (topicID == -1) {
            topicID = stdTopicSemRepo.getTopicIDFromStudentID(stdRepo.getStdIDFromUserID(userSession.getUserID()));
        }
        if (userSession.isStudent()) {
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
    public List<TaskComment> createTasktest(@RequestParam("taskid") Integer taskID){
        return taskService.getTaskComment(taskID);
    }

    private List<String> files = new ArrayList<String>();

    @PostMapping("/post")
    public ResponseEntity<String> handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") Integer taskId
    ) {
        String message;
        try {
            System.out.println(taskId);
            storageService.storeTask(file, taskId);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/getallfiles")
    public ResponseEntity<List<String>> getListFiles(Model model) {
        List<String> fileNames = files
                .stream().map(fileName -> MvcUriComponentsBuilder
                        .fromMethodName(TaskController.class, "getFile", fileName).build().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

}
