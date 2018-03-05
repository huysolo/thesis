package hcmut.thesis.managethesis.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "faculty", schema = "thesis", catalog = "")
public class FacultyEntity {
    private int idFaculty;
    private String name;

    @Id
    @Column(name = "id_faculty")
    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyEntity that = (FacultyEntity) o;
        return idFaculty == that.idFaculty &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idFaculty, name);
    }
}
