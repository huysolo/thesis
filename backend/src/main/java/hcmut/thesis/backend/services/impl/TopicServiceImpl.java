package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.Professor;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.models.TopicMission;
import hcmut.thesis.backend.models.TopicRequirement;
import hcmut.thesis.backend.modelview.TopicDetail;
import hcmut.thesis.backend.repositories.*;
import hcmut.thesis.backend.services.IUserDAO;
import hcmut.thesis.backend.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    IUserDAO userDAO;

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
        Optional<Topic> optionalTopic = topicRepo.findById(topId);
        if (optionalTopic.isPresent()){
            Topic topic = optionalTopic.get();
            List<TopicMission> topicMissionList = topicMissionRepo.findAllByTopicId(topId);
            List<TopicRequirement> topicRequirementList = topicReqRepo.findAllByTopicId(topId);
            return null;
        }
        return null;
    }

    @Override
    public void setTopicDetail(TopicDetail topicDetail) {
        Topic topic = topicDetail.getTopic();
        topic.setIdFaculty(userDAO.getCurrentUserFalcuty());
        topicRepo.save(topic);
        topicDetail.getTopicMission().forEach(topicMissionDetail -> topicMissionDetail.setIdTopic(topic.getIdTop()));
        topicMissionRepo.saveAll(topicDetail.getTopicMission());
        topicDetail.getTopicRequirement().forEach(topicReqDetail -> topicReqDetail.setIdTopic(topic.getIdTop()));
        topicReqRepo.saveAll(topicDetail.getTopicRequirement());
    }
}
