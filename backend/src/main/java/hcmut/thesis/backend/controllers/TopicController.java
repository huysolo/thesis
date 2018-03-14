package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("topic")
public class TopicController {
    @Autowired
    TopicService topicService;
    @RequestMapping(value = "listTopic", method = RequestMethod.GET)
    List<Topic> getListTopic(){
        return topicService.getListTopicBySemester(171);
    }
}
