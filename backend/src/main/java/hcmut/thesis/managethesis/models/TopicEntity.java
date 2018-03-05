package hcmut.thesis.managethesis.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "topic", schema = "thesis", catalog = "")
public class TopicEntity {
    private int idTop;
    private String title;
    private int stNumLimit;
    private String sumary;
    private int idProf;
    private int idFaculty;

    @Id
    @Column(name = "id_top")
    public int getIdTop() {
        return idTop;
    }

    public void setIdTop(int idTop) {
        this.idTop = idTop;
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
    @Column(name = "st_num_limit")
    public int getStNumLimit() {
        return stNumLimit;
    }

    public void setStNumLimit(int stNumLimit) {
        this.stNumLimit = stNumLimit;
    }

    @Basic
    @Column(name = "sumary")
    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    @Basic
    @Column(name = "id_prof")
    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    @Basic
    @Column(name = "id_faculty")
    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicEntity that = (TopicEntity) o;
        return idTop == that.idTop &&
                stNumLimit == that.stNumLimit &&
                idProf == that.idProf &&
                idFaculty == that.idFaculty &&
                Objects.equals(title, that.title) &&
                Objects.equals(sumary, that.sumary);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTop, title, stNumLimit, sumary, idProf, idFaculty);
    }
}
