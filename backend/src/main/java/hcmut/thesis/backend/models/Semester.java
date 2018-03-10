package hcmut.thesis.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Semester {
    private int idSemester;
    private int idTopic;
    private String semesterName;
    private int year;

    @Id
    @Column(name = "id_semester")
    public int getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(int idSemester) {
        this.idSemester = idSemester;
    }

    @Basic
    @Column(name = "id_topic")
    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    @Basic
    @Column(name = "semester_name")
    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return idSemester == semester.idSemester &&
                idTopic == semester.idTopic &&
                year == semester.year &&
                Objects.equals(semesterName, semester.semesterName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idSemester, idTopic, semesterName, year);
    }
}
