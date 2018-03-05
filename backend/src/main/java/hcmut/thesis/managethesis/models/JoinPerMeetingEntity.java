package hcmut.thesis.managethesis.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "join_per_meeting", schema = "thesis", catalog = "")
@IdClass(JoinPerMeetingEntityPK.class)
public class JoinPerMeetingEntity {
    private int idStudent;
    private int idMeeting;

    @Id
    @Column(name = "id_student")
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Id
    @Column(name = "id_meeting")
    public int getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(int idMeeting) {
        this.idMeeting = idMeeting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinPerMeetingEntity that = (JoinPerMeetingEntity) o;
        return idStudent == that.idStudent &&
                idMeeting == that.idMeeting;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idStudent, idMeeting);
    }
}
