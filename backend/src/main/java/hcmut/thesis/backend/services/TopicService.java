package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.modelview.TopicDetail;

import java.util.List;
public interface TopicService {
    List<Topic> getListTopicBySemester(Integer semesterNo, Integer profId);
    TopicDetail getTopicDetailById(Integer topId);
}
