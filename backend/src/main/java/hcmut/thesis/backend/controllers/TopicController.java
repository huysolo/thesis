package hcmut.thesis.backend.controllers;

import com.google.gson.Gson;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.modelview.TopicDetail;
import hcmut.thesis.backend.modelview.UserSession;
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

    @RequestMapping(value = "listTopic", method = RequestMethod.GET)
    List<Topic> getListTopic(
            @RequestParam(value = "semno", required = false) Integer semno,
            @RequestParam(value = "profId", required = false) Integer profId
    ){
        try {
            if (userSession.isUser()) {
                return topicService.getListTopicBySemester(semno, profId);
            } else {
                return null;
            }
        } catch (NullPointerException e){
            return null;
        }
    }
    @RequestMapping(value = "recentTopics", method = RequestMethod.GET)
    List<Topic> getListTopicRecent(
            @RequestParam(value = "profId", required = false) Integer profId
    ){
        try {
            if (userSession.isUser()) {
                return topicService.getListTopicBySemester(profId);
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
            return topicService.setTopicDetail(topicDetailJS);
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
        return topicService.getAppliedTopic(semno);
    }

}
