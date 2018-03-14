package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.repositories.TopicRepo;
import hcmut.thesis.backend.repositories.TopicSemesterRepo;
import hcmut.thesis.backend.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicRepo topicRepo;
    @Autowired
    TopicSemesterRepo topicSemesterRepo;
    @Override
    public List<Topic> getListTopicBySemester(Integer semesterNo) {
        Integer topId = topicSemesterRepo.findTopBySemesterNo(semesterNo);
        System.out.println("sasdsadasdasdasdasdasdsadas" + topId);
        return topicRepo.findAllById(Collections.singleton(topId));
    }
}
