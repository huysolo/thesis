package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.models.TopicMission;
import hcmut.thesis.backend.models.TopicPerSemester;
import hcmut.thesis.backend.models.TopicRequirement;
import hcmut.thesis.backend.modelview.TopicDetail;
import hcmut.thesis.backend.repositories.*;
import hcmut.thesis.backend.services.IUserDAO;
import hcmut.thesis.backend.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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

    @Autowired
    SemesterRepo semesterRepo;

    @Autowired
    EntityManager entityManager;

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
    public HttpStatus setTopicDetail(TopicDetail topicDetail) {
        List<Integer> semesters = semesterRepo.getCurrentApplySemester();
        if (semesters.size() == 0){
            return HttpStatus.EXPECTATION_FAILED;
        }
        Topic topic = topicDetail.getTopic();
//        topic.setIdProf(topicDetail.getTopic().getIdProf());
//        topic.setStNumLimit(topicDetail.getTopic().getStNumLimit());
//        topic.setSumary(topicDetail.getTopic().getSumary());
//        topic.setTitle(topicDetail.getTopic().getTitle());
        topic.setIdFaculty(userDAO.getCurrentUserFalcuty());
        topicRepo.saveAndFlush(topic);


        topicDetail.getTopicMission().forEach(topicMissionDetail -> topicMissionDetail.setIdTopic(topic.getIdTop()));
        topicMissionRepo.saveAll(topicDetail.getTopicMission());
        topicDetail.getTopicRequirement().forEach(topicReqDetail -> topicReqDetail.setIdTopic(topic.getIdTop()));
        topicReqRepo.saveAll(topicDetail.getTopicRequirement());
        TopicPerSemester topicPerSemester = new TopicPerSemester();
        topicPerSemester.setIdTopic(topic.getIdTop());
        topicPerSemester.setSemesterNo(semesters.get(0));
        topicSemesterRepo.save(topicPerSemester);
        return HttpStatus.CREATED;
    }
}
