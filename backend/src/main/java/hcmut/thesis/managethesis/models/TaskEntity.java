package hcmut.thesis.managethesis.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "task", schema = "thesis", catalog = "")
public class TaskEntity {
    private int idTask;
    private String title;
    private String description;
    private Timestamp deadline;
    private Integer idTopicSem;

    @Id
    @Column(name = "id_task")
    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "deadline")
    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
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
        TaskEntity that = (TaskEntity) o;
        return idTask == that.idTask &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(deadline, that.deadline) &&
                Objects.equals(idTopicSem, that.idTopicSem);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTask, title, description, deadline, idTopicSem);
    }
}
