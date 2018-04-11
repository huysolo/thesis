package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.*;
import hcmut.thesis.backend.modelview.TopicDetail;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.repositories.*;
import hcmut.thesis.backend.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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



    @Override
    public List<Topic> getListTopicBySemester(Integer semesterNo, Integer profId) {
        List<Topic>  topicList;
        if (semesterNo != null && semesterNo != -1){
            topicList = topicRepo.findTopBySemesterNo(semesterNo);
        } else {
            topicList = topicRepo.findAll();
        }
        if (profId != null && profId != -1) topicList.removeIf(topic -> topic.getIdProf() != profId);
        return topicList;
    }

    @Override
    public List<Topic> getListTopicBySemester(Integer profId) {
        List<Integer> semNo = semesterRepo.getCurrentApplySemester();
        if(semNo.size() == 0) {
            return null;
        }
        if (userSession.isProf()){
            profId = userSession.getProf().getIdProfessor();
        }
        return getListTopicBySemester(semNo.get(0), profId);
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
        topic.setIdFaculty(userSession.getCurrentUserFalcuty());
        topic.setSemesterNo(semesters.get(0));
        topicRepo.saveAndFlush(topic);


        topicDetail.getTopicMission().forEach(topicMissionDetail -> topicMissionDetail.setIdTopic(topic.getIdTop()));
        topicMissionRepo.saveAll(topicDetail.getTopicMission());
        topicDetail.getTopicRequirement().forEach(topicReqDetail -> topicReqDetail.setIdTopic(topic.getIdTop()));
        topicReqRepo.saveAll(topicDetail.getTopicRequirement());
        return HttpStatus.CREATED;
    }

    @Override
    public HttpStatus applyToTopic(Integer topId, Integer studentId) {
        List<Integer> semesters = semesterRepo.getCurrentApplySemester();
        Optional<Topic> topicOp = topicRepo.findById(topId);
        if (semesters.size() == 0 || !topicOp.isPresent() || !semesters.get(0).equals(topicOp.get().getSemesterNo())) {
            return HttpStatus.EXPECTATION_FAILED;
        }
        List<Topic> topicList = topicRepo.findTopBySemesterNo(semesters.get(0));
        for (Topic topic :
                topicList) {
            if(studentTopicSemRepo.getStudentTopicSemByAll(studentId, topic.getIdTop()).size() > 0){
                return HttpStatus.EXPECTATION_FAILED;
            }
        }
        StudentTopicSem studentTopicSem = new StudentTopicSem();
        studentTopicSem.setIdStudent(studentId);
        studentTopicSem.setIdTopicSem(topicOp.get().getIdTop());
        studentTopicSemRepo.save(studentTopicSem);
        return HttpStatus.CREATED;
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
}
