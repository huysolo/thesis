package hcmut.thesis.managethesis.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "thesis", catalog = "")
public class StudentEntity {
    private int idStudent;
    private int idUser;

    @Id
    @Column(name = "id_student")
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Basic
    @Column(name = "id_user")
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
        StudentEntity that = (StudentEntity) o;
        return idStudent == that.idStudent &&
                idUser == that.idUser;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idStudent, idUser);
    }
}
