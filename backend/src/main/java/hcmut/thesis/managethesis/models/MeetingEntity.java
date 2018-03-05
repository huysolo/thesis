package hcmut.thesis.managethesis.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "meeting", schema = "thesis", catalog = "")
public class MeetingEntity {
    private int idMeeting;
    private String note;
    private String content;
    private Integer studentCount;
    private Timestamp meetingTime;
    private int approve;
    private String location;
    private Integer idTopicSem;

    @Id
    @Column(name = "id_meeting")
    public int getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(int idMeeting) {
        this.idMeeting = idMeeting;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "student_count")
    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    @Basic
    @Column(name = "meeting_time")
    public Timestamp getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(Timestamp meetingTime) {
        this.meetingTime = meetingTime;
    }

    @Basic
    @Column(name = "approve")
    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "id_topic_sem")
    public Integer getIdTopicSem() {
        return idTopicSem;
    }

    public void setIdTopicSem(Integer idTopicSem) {
        this.idTopicSem = idTopicSem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingEntity that = (MeetingEntity) o;
        return idMeeting == that.idMeeting &&
                approve == that.approve &&
                Objects.equals(note, that.note) &&
                Objects.equals(content, that.content) &&
                Objects.equals(studentCount, that.studentCount) &&
                Objects.equals(meetingTime, that.meetingTime) &&
                Objects.equals(location, that.location) &&
                Objects.equals(idTopicSem, that.idTopicSem);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idMeeting, note, content, studentCount, meetingTime, approve, location, idTopicSem);
    }
}
