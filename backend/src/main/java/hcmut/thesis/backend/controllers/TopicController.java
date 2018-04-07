package hcmut.thesis.backend.controllers;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.models.TopicRequirement;
import hcmut.thesis.backend.modelview.TopicDetail;
import hcmut.thesis.backend.services.TopicService;
import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.plugin.javascript.JSObject;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("topic")
public class TopicController {
    @Autowired
    TopicService topicService;
    @RequestMapping(value = "listTopic", method = RequestMethod.GET)
    List<Topic> getListTopic(
            @RequestParam(value = "semno", required = false) Integer semno,
            @RequestParam(value = "profId", required = false) Integer profId
    ){
        try {
            return topicService.getListTopicBySemester(semno, profId);
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

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    Integer setTopicDetail(@RequestBody String topicDetail) {
        Gson obj = new Gson();
        TopicDetail topicDetailJS = obj.fromJson(topicDetail, TopicDetail.class);
        topicService.setTopicDetail(topicDetailJS);
        return 123;
    }

}
