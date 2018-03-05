package hcmut.thesis.managethesis.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "professor", schema = "thesis", catalog = "")
public class ProfessorEntity {
    private int idProfessor;
    private int idUser;

    @Id
    @Column(name = "id_professor")
    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
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
        ProfessorEntity that = (ProfessorEntity) o;
        return idProfessor == that.idProfessor &&
                idUser == that.idUser;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProfessor, idUser);
    }
}
