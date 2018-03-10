package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.repositories.TopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("topic")
public class TopicController {
    @Autowired
    TopicRepo topicRepo;
    @RequestMapping(value = "listTopic", method = RequestMethod.GET)
    List<Topic> getListTopic(){
        return topicRepo.findAll();
    }
}
