package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.StudentTopicSem;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.models.TopicMission;
import hcmut.thesis.backend.models.TopicRequirement;
import hcmut.thesis.backend.modelview.TopicDetail;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.repositories.*;
import hcmut.thesis.backend.services.ITopicDAO;
import hcmut.thesis.backend.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicRepo topicRepo;

    @Autowired
    TopicMissionRepo topicMissionRepo;

    @Autowired
    TopicReqRepo topicReqRepo;

    @Autowired
    ProfessorRepo professorRepo;

    @Autowired
    UserSession userSession;

    @Autowired
    SemesterRepo semesterRepo;

    @Autowired
    StudentTopicSemRepo studentTopicSemRepo;

    @Autowired
    JdbcTemplate template;

    @Autowired
    ITopicDAO topicDAO;

    @Override
    public List<Topic> getListTopicBySemester(Integer idFal, Integer semesterNo, Integer profId, Boolean aval, Integer specialize) {
        aval = aval == null ? true : aval;
        return topicDAO.getFilteredTopicList(idFal, profId, specialize, semesterNo);
    }

    @Override
    public List<Topic> getListRecentTopicBySemester(Integer profId, Boolean aval, Integer specialize) {
        List<Integer> semNo = semesterRepo.getCurrentApplySemester();
        if(semNo.size() == 0) {
            return null;
        }
        Integer idFal = null;
        if (userSession.isUser()) {
            idFal = userSession.getCurrentUserFalcuty();
        }
        return getListTopicBySemester(idFal, semNo.get(0), profId, aval, specialize);

    }

    @Override
    public TopicDetail getTopicDetailById(Integer topId) {
        Optional<Topic> optionalTopic = topicRepo.findById(topId);
        if (optionalTopic.isPresent()){
            Topic topic = optionalTopic.get();
            List<TopicMission> topicMissionList = topicMissionRepo.findAllByTopicId(topId);
            List<TopicRequirement> topicRequirementList = topicReqRepo.findAllByTopicId(topId);
            return new TopicDetail(topic, topicMissionList, topicRequirementList);
        }
        return null;
    }

    @Override
    public Topic setTopicDetail(TopicDetail topicDetail, Boolean publish) {

        Topic topic = topicDetail.getTopic();
        topic.setStudentCount(0);

        if (publish){
            topic = setPublish(topic);
        }
        if (topic == null) {
            return null;
        }
        topicRepo.saveAndFlush(topic);
        for (TopicMission topicMis :
                topicDetail.getTopicMission()) {
            topicMis.setIdTopic(topic.getIdTop());
        }
        topicMissionRepo.saveAll(topicDetail.getTopicMission());

        for (TopicRequirement topicRequirement :
                topicDetail.getTopicRequirement()) {
            topicRequirement.setIdTopic(topic.getIdTop());
        }
        topicReqRepo.saveAll(topicDetail.getTopicRequirement());

        return topic;
    }



    @Override
    public Topic applyToTopic(Integer topId, Integer studentId) {
        List<Integer> semesters = semesterRepo.getCurrentApplySemester();
        Optional<Topic> topicOp = topicRepo.findById(topId);
        if (semesters.size() == 0 || !topicOp.isPresent() || !semesters.get(0).equals(topicOp.get().getSemesterNo())) {
            return null;
        }
        List<Topic> topicList = topicRepo.findTopBySemesterNo(semesters.get(0));
        for (Topic topic :
                topicList) {
            if(studentTopicSemRepo.getStudentTopicSemByAll(studentId, topic.getIdTop()).size() > 0){
                return null;
            }
            if (!availableTopic(topic)){
                return null;
            }
        }
        topicOp.get().setStudentCount(topicOp.get().getStudentCount() + 1);
        topicRepo.save(topicOp.get());
        StudentTopicSem studentTopicSem = new StudentTopicSem();
        studentTopicSem.setIdStudent(studentId);
        studentTopicSem.setIdTopicSem(topicOp.get().getIdTop());
        studentTopicSemRepo.save(studentTopicSem);
        return topicOp.get();
    }

    @Override
    public Topic getAppliedTopic(Integer semesterNo, Integer studendId) {
        if (semesterNo == null){
            List<Integer> semesters = semesterRepo.getCurrentApplySemester();
            if (semesters.size() == 0) {
                return null ;
            }
            semesterNo = semesters.get(0);
        }
        List<Topic> topicList = topicRepo.findTopBySemesterNo(semesterNo);
        for (Topic topic :
                topicList) {
            if (studentTopicSemRepo.getStudentTopicSemByAll(studendId, topic.getIdTop()).size() > 0){
                return topic;
            }
        }
        return null;
    }

    @Override
    public Integer numberOfApply(Integer topicId) {
        return studentTopicSemRepo.getAllStudentByIdTopicSem(topicId).size();
    }

    @Override
    public List<Topic> getDraftTopics(Integer profId) {
        return topicRepo.findAllUnPublish(profId);
    }

    @Override
    public Boolean availableTopic(Topic topic) {
        return numberOfApply(topic.getIdTop()) < topic.getStNumLimit();
    }

    @Override
    public Topic rejectTopic(Integer topId, Integer studentId) {
        List<Integer> semNo = semesterRepo.getCurrentApplySemester();
        if(semNo.size() == 0) {
            return null;
        }
        Optional<Topic> topic = topicRepo.findById(topId);
        if (topic.isPresent()) {
            topic.get().setStudentCount(topic.get().getStudentCount() - 1);
            topicRepo.save(topic.get());
            StudentTopicSem studentTopicSem = new StudentTopicSem();
            studentTopicSem.setIdTopicSem(topId);
            studentTopicSem.setIdStudent(studentId);
            studentTopicSemRepo.delete(studentTopicSem);
            return topic.get();
        } else {
            return null;
        }

    }

    @Override
    public Topic publish(Integer topicId) {
        try{
            Topic topic = topicRepo.findById(topicId).get();
            topicRepo.save(topic);
            System.out.println(topic.getSemesterNo());
            return topic;
        } catch (Exception e){
            return null;
        }
    }

    private Topic setPublish(Topic topic) {
        List<Integer> semesters = semesterRepo.getCurrentApplySemester();
        if (semesters.size() == 0){
            return null;
        }
        System.out.println(semesters.get(0));
        topic.setSemesterNo(semesters.get(0));
        topic.setPublishDate(new Timestamp(System.currentTimeMillis()));
        return topic;
    }

    @Override
    public List<Topic> getListTopicReview(Integer semNo, Integer profId) {
        if (semNo == null) {
            return topicDAO.getListReviewTopicByProfId(profId);
        }
        return topicDAO.getListReviewTopicByProfIdAndSemesterNo(profId, semNo);
    }

    @Override
    public Topic edit(TopicDetail topicDetail) {
        try {
            Topic topic = deleteTopicMissionAndRequirement(topicDetail.getTopic().getIdTop());
            if (topic == null) {
                return null;
            }
            setTopicDetail(topicDetail, false);
            return topic;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public Integer delete(Integer topicId) {
        try {
            Topic topic = deleteTopicMissionAndRequirement(topicId);
            assert topic != null;
            topicRepo.delete(topic);
            return topicId;
        } catch (NullPointerException e) {
            return null;
        }
    }

    private Topic deleteTopicMissionAndRequirement(Integer topicId) throws NullPointerException {
        TopicDetail topicDetail = getTopicDetailById(topicId);
        if (userSession.getProf().getIdProfessor() != topicDetail.getTopic().getIdProf()){
            return null;
        }
        if (topicDetail.getTopic().getSemesterNo() != null) {
            return null;
        }
        topicDetail.getTopicMission().forEach(topicMission -> {
            topicMissionRepo.delete(topicMission);
        });
        topicDetail.getTopicRequirement().forEach(topicRequirement -> {
            topicReqRepo.delete(topicRequirement);
        });
        return topicDetail.getTopic();
    }

}
