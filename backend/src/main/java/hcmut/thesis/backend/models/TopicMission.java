package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "topic_mission", schema = "thesis", catalog = "")
public class TopicMission {
    private int idMission;
    private int topicId;
    private String detail;

    @Id
    @Column(name = "id_mission")
    public int getIdMission() {
        return idMission;
    }

    public void setIdMission(int idMission) {
        this.idMission = idMission;
    }

    @Basic
    @Column(name = "topic_id")
    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Basic
    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicMission that = (TopicMission) o;
        return idMission == that.idMission &&
                topicId == that.topicId &&
                Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idMission, topicId, detail);
    }
}
