package hcmut.thesis.managethesis.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class ChatGroupEntityPK implements Serializable {
    private Timestamp time;
    private int idTopicSem;
    private int idUser;

    @Column(name = "time")
    @Id
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Column(name = "id_topic_sem")
    @Id
    public int getIdTopicSem() {
        return idTopicSem;
    }

    public void setIdTopicSem(int idTopicSem) {
        this.idTopicSem = idTopicSem;
    }

    @Column(name = "id_user")
    @Id
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatGroupEntityPK that = (ChatGroupEntityPK) o;
        return idTopicSem == that.idTopicSem &&
                idUser == that.idUser &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(time, idTopicSem, idUser);
    }
}
