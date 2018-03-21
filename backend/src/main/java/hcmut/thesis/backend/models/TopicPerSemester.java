package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "topic_per_semester", schema = "thesis", catalog = "")
public class TopicPerSemester {
    private int idTopicSemester;
    private int score;
    private Integer semesterNo;
    private int idTopic;

    @Id
    @Column(name = "id_topic_semester")
    public int getIdTopicSemester() {
        return idTopicSemester;
    }

    public void setIdTopicSemester(int idTopicSemester) {
        this.idTopicSemester = idTopicSemester;
    }

    @Basic
    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Basic
    @Column(name = "semester_no")
    public Integer getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(Integer semesterNo) {
        this.semesterNo = semesterNo;
    }

    @Basic
    @Column(name = "id_topic")
    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicPerSemester that = (TopicPerSemester) o;
        return idTopicSemester == that.idTopicSemester &&
                score == that.score &&
                idTopic == that.idTopic &&
                Objects.equals(semesterNo, that.semesterNo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTopicSemester, score, semesterNo, idTopic);
    }
}
