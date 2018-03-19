package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.models.TopicMission;
import hcmut.thesis.backend.models.TopicRequirement;
import hcmut.thesis.backend.modelview.TopicDetail;
import hcmut.thesis.backend.repositories.TopicMissionRepo;
import hcmut.thesis.backend.repositories.TopicRepo;
import hcmut.thesis.backend.repositories.TopicReqRepo;
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

    @Autowired
    TopicMissionRepo topicMissionRepo;

    @Autowired
    TopicReqRepo topicReqRepo;

    @Override
    public List<Topic> getListTopicBySemester(Integer semesterNo) {
        List<Integer> topId = topicSemesterRepo.findTopBySemesterNo(semesterNo);
        return topicRepo.findAllById(topId);
    }

    @Override
    public TopicDetail getTopicDetailById(Integer topId) {
        Topic topic = topicRepo.findById(topId).get();
        List<TopicMission> topicMissionList = topicMissionRepo.findAllByTopicId(topId);
        List<TopicRequirement> topicRequirementList = topicReqRepo.findAllByTopicId(topId);
        return new TopicDetail(topic, topicMissionList, topicRequirementList);
    }
}
