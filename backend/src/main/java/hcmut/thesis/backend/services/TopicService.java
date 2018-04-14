package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.modelview.TopicDetail;
import org.springframework.http.HttpStatus;

import java.util.List;
public interface TopicService {
    List<Topic> getListTopicBySemester(Integer semesterNo, Integer profId, Boolean available);
    List<Topic> getListTopicBySemester(Integer profId, Boolean aval);
    TopicDetail getTopicDetailById(Integer topId);
    HttpStatus setTopicDetail(TopicDetail topicDetail, Boolean publish);
    HttpStatus applyToTopic(Integer topId, Integer studentId);
    Topic getAppliedTopic(Integer semesterNo, Integer studendId);
    Integer numberOfApply(Integer topicId);
    List<Topic> getDraftTopics(Integer profId);
    Boolean availableTopic(Topic topic);
    HttpStatus rejectTopic(Integer topId, Integer studentId);
    HttpStatus publish(Integer topicId);
    HttpStatus edit(TopicDetail topicDetail);
    HttpStatus delete(Integer topicId);

}
