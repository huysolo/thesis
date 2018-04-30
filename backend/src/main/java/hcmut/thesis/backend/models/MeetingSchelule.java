package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "meeting_schelule", schema = "thesis", catalog = "")
public class MeetingSchelule {
    private int idMeetingSchelule;
    private Integer status;
    private String note;
    private Timestamp meetingTime;
    private Integer idMeeting;
    private String location;

    @Id
    @Column(name = "id_meeting_schelule")
    public int getIdMeetingSchelule() {
        return idMeetingSchelule;
    }

    public void setIdMeetingSchelule(int idMeetingSchelule) {
        this.idMeetingSchelule = idMeetingSchelule;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    @Column(name = "meeting_time")
    public Timestamp getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(Timestamp meetingTime) {
        this.meetingTime = meetingTime;
    }

    @Basic
    @Column(name = "id_meeting")
    public Integer getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(Integer idMeeting) {
        this.idMeeting = idMeeting;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingSchelule that = (MeetingSchelule) o;
        return idMeetingSchelule == that.idMeetingSchelule &&
                Objects.equals(status, that.status) &&
                Objects.equals(note, that.note) &&
                Objects.equals(meetingTime, that.meetingTime) &&
                Objects.equals(idMeeting, that.idMeeting) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idMeetingSchelule, status, note, meetingTime, idMeeting, location);
    }
}
