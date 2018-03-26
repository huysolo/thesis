package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.Professor;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.models.TopicMission;
import hcmut.thesis.backend.models.TopicRequirement;
import hcmut.thesis.backend.modelview.TopicDetail;
import hcmut.thesis.backend.repositories.*;
import hcmut.thesis.backend.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicRepo topicRepo;
    @Autowired
    TopicSemesterRepo topicSemesterRepo;

    @Autowired
    TopicMissionRepo topicMissionRepo;

    @Autowired
    TopicReqRepo topicReqRepo;

    @Autowired
    ProfessorRepo professorRepo;

    @Override
    public List<Topic> getListTopicBySemester(Integer semesterNo, Integer profId) {
        List<Topic>  topicList;
        if (semesterNo != null && semesterNo != -1){
            List<Integer> topId = topicSemesterRepo.findTopBySemesterNo(semesterNo);
            topicList = topicRepo.findAllById(topId);
        } else {
            topicList = topicRepo.findAll();
        }
        if (profId != null && profId != -1) topicList.removeIf(topic -> topic.getIdProf() != profId);
        return topicList;
    }

    @Override
    public TopicDetail getTopicDetailById(Integer topId) {
        Topic topic = topicRepo.findById(topId).get();
        List<TopicMission> topicMissionList = topicMissionRepo.findAllByTopicId(topId);
        List<TopicRequirement> topicRequirementList = topicReqRepo.findAllByTopicId(topId);
        return new TopicDetail(topic, topicMissionList, topicRequirementList);
    }
}
