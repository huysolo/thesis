package hcmut.thesis.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Semester {
    private int idSemester;
    private int idTopic;
    private String semesterName;
    private int year;
    private int semesterNo;
    private Timestamp applyOpenDate;
    private Integer applyCloseDate;
    private Timestamp endDate;
    private Timestamp startDate;

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

    @Id
    @Column(name = " semester_no")
    public int getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }

    @Basic
    @Column(name = "apply_open_date")
    public Timestamp getApplyOpenDate() {
        return applyOpenDate;
    }

    public void setApplyOpenDate(Timestamp applyOpenDate) {
        this.applyOpenDate = applyOpenDate;
    }

    @Basic
    @Column(name = "apply_close_date")
    public Integer getApplyCloseDate() {
        return applyCloseDate;
    }

    public void setApplyCloseDate(Integer applyCloseDate) {
        this.applyCloseDate = applyCloseDate;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
}
