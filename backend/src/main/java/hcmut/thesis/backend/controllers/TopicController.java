package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.modelview.TopicDetail;
import hcmut.thesis.backend.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("topic")
public class TopicController {
    @Autowired
    TopicService topicService;
    @RequestMapping(value = "listTopic", method = RequestMethod.GET)
    List<Topic> getListTopic(@RequestParam(value = "semno", required = true) Integer semno){
        try {
            return topicService.getListTopicBySemester(semno);
        }catch (NullPointerException e){
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
}
