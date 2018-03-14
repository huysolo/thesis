package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.Topic;

import java.util.List;
public interface TopicService {
    List<Topic> getListTopicBySemester(Integer semesterNo);

}
