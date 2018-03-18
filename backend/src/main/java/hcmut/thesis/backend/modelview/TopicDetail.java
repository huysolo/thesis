package hcmut.thesis.backend.modelview;

import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.models.TopicMission;
import hcmut.thesis.backend.models.TopicRequirement;

import java.util.List;

public class TopicDetail {
    public Topic getTopic() {
        return topic;
    }

    public List<TopicMission> getTopicMission() {
        return topicMission;
    }

    public List<TopicRequirement> getTopicRequirements() {
        return topicRequirements;
    }

    private Topic topic;
    private List<TopicMission> topicMission;
    private List<TopicRequirement> topicRequirements;

    public TopicDetail(Topic topic, List<TopicMission> topicMission, List<TopicRequirement> topicRequirements) {
        this.topic = topic;
        this.topicMission = topicMission;
        this.topicRequirements = topicRequirements;
    }
}
