package hcmut.thesis.backend.controllers;

import com.google.gson.Gson;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.modelview.TopicDetail;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.services.ITopicDAO;
import hcmut.thesis.backend.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("topic")
public class TopicController {
    @Autowired
    TopicService topicService;

    @Autowired
    UserSession userSession;

    @Autowired
    ITopicDAO topicDAO;

    @RequestMapping(value = "listTopic", method = RequestMethod.GET)
    List<Topic> getListTopic(
            @RequestParam(value = "semno", required = false) Integer semno,
            @RequestParam(value = "profId", required = false) Integer profId,
            @RequestParam(value = "avail", required = false) Boolean available,
            @RequestParam(value = "spec", required = false) Integer specialize

    ){
        if (userSession.isUser()) {
            return topicService.getListTopicBySemester(userSession.getCurrentUserFalcuty(), semno, profId, available, specialize);
        } else {
            return null;
        }
//        try {
//
//        } catch (NullPointerException e){
//            return null;
//        }
    }

    @RequestMapping(value = "listDraft", method = RequestMethod.GET)
    List<Topic> getListTopic(
    ){
        try {
            if (userSession.isProf()) {
                return topicService.getDraftTopics(userSession.getProf().getIdProfessor());
            } else {
                return null;
            }
        } catch (NullPointerException e){
            return null;
        }
    }
    @RequestMapping(value = "recentTopics", method = RequestMethod.GET)
    List<Topic> getListTopicRecent(
            @RequestParam(value = "profId", required = false) Integer profId,
            @RequestParam(value = "avail", required = false) Boolean available,
            @RequestParam(value = "spec", required = false) Integer specialize
    ){
        try {
            if (userSession.isUser()) {
                return topicService.getListRecentTopicBySemester(profId, available, specialize);
            } else {
                return null;
            }
        } catch (NullPointerException e){
            return null;
        }
    }

    @RequestMapping(value = "topicDetail",method = RequestMethod.GET)
    TopicDetail getTopicDetail(@RequestParam(value = "topid",required = true) Integer topId){
        try {
            return topicService.getTopicDetailById(topId);
        } catch (NullPointerException e){
            return null;
        }
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    HttpStatus setTopicDetail(@RequestBody String topicDetail) {
        if (!userSession.isProf()){
            return HttpStatus.FORBIDDEN;
        }
        try {
            Gson obj = new Gson();
            TopicDetail topicDetailJS = obj.fromJson(topicDetail, TopicDetail.class);
            return topicService.setTopicDetail(topicDetailJS, !topicDetailJS.getDraft());
        } catch (EntityExistsException e){
            return HttpStatus.EXPECTATION_FAILED;
        }
    }

    @RequestMapping(value = "publish", method = RequestMethod.POST)
    @ResponseBody
    HttpStatus setTopicDetail(@RequestBody Integer topicId) {
        if (!userSession.isProf()){
            return HttpStatus.FORBIDDEN;
        }
        try {
            return topicService.publish(topicId);
        } catch (EntityExistsException e){
            return HttpStatus.EXPECTATION_FAILED;
        }

    }

    @PostMapping(value = "apply")
    HttpStatus applyToTopic(@RequestBody Integer topicId){
        if(!userSession.isStudent()){
            return HttpStatus.FORBIDDEN;
        }
        return topicService.applyToTopic(topicId, userSession.getStudent().getIdUser());
    }

    @GetMapping(value = "appliedTopic")
    Topic getAppliedTopic(@RequestParam(value = "semno", required = false) Integer semno){
        if(!userSession.isStudent()){
            return null;
        }
        return topicService.getAppliedTopic(semno,  userSession.getStudent().getIdUser());
    }
    @PostMapping(value = "reject")
    HttpStatus rejectToTopic(@RequestBody Integer topicId){
        if(!userSession.isStudent()){
            return HttpStatus.FORBIDDEN;
        }
        return topicService.rejectTopic(topicId, userSession.getStudent().getIdUser());
    }

}
