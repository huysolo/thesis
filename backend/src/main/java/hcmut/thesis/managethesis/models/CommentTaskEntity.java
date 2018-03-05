package hcmut.thesis.managethesis.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "comment_task", schema = "thesis", catalog = "")
@IdClass(CommentTaskEntityPK.class)
public class CommentTaskEntity {
    private Timestamp time;
    private int idTask;
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
    @Column(name = "id_task")
    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
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
        CommentTaskEntity that = (CommentTaskEntity) o;
        return idTask == that.idTask &&
                idUser == that.idUser &&
                Objects.equals(time, that.time) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(time, idTask, idUser, content);
    }
}
