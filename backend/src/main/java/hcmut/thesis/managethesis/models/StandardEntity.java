package hcmut.thesis.managethesis.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "standard", schema = "thesis", catalog = "")
public class StandardEntity {
    private int idStandard;
    private Integer stName;
    private Integer idProf;

    @Id
    @Column(name = "id_standard")
    public int getIdStandard() {
        return idStandard;
    }

    public void setIdStandard(int idStandard) {
        this.idStandard = idStandard;
    }

    @Basic
    @Column(name = "st_name")
    public Integer getStName() {
        return stName;
    }

    public void setStName(Integer stName) {
        this.stName = stName;
    }

    @Basic
    @Column(name = "id_prof")
    public Integer getIdProf() {
        return idProf;
    }

    public void setIdProf(Integer idProf) {
        this.idProf = idProf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardEntity that = (StandardEntity) o;
        return idStandard == that.idStandard &&
                Objects.equals(stName, that.stName) &&
                Objects.equals(idProf, that.idProf);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idStandard, stName, idProf);
    }
}
