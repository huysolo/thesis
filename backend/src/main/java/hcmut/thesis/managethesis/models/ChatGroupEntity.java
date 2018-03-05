package hcmut.thesis.managethesis.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "chat_group", schema = "thesis", catalog = "")
@IdClass(ChatGroupEntityPK.class)
public class ChatGroupEntity {
    private Timestamp time;
    private int idTopicSem;
    private int idUser;
    private String content;

    @Id
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Id
    @Column(name = "id_topic_sem")
    public int getIdTopicSem() {
        return idTopicSem;
    }

    public void setIdTopicSem(int idTopicSem) {
        this.idTopicSem = idTopicSem;
    }

    @Id
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatGroupEntity that = (ChatGroupEntity) o;
        return idTopicSem == that.idTopicSem &&
                idUser == that.idUser &&
                Objects.equals(time, that.time) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(time, idTopicSem, idUser, content);
    }
}
