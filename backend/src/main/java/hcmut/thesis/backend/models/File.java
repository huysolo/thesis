package hcmut.thesis.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class File {
    private int idFile;
    private String name;
    private Timestamp uploadDate;
    private String idUser;
    private Integer idTask;

    @Id
    @Column(name = "id_file")
    public int getIdFile() {
        return idFile;
    }

    public void setIdFile(int idFile) {
        this.idFile = idFile;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "upload_date")
    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Basic
    @Column(name = "id_user")
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "id_task")
    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return idFile == file.idFile &&
                Objects.equals(name, file.name) &&
                Objects.equals(uploadDate, file.uploadDate) &&
                Objects.equals(idUser, file.idUser) &&
                Objects.equals(idTask, file.idTask);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idFile, name, uploadDate, idUser, idTask);
    }
}
