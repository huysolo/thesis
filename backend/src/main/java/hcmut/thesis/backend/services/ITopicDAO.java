package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.Topic;

import java.util.List;

public interface ITopicDAO {
    List<Topic> getFilteredTopicList(Integer idFal, Integer idProf, Integer idSpec, Integer semNo);
}
