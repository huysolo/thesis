package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.Semester;
import hcmut.thesis.backend.modelview.ProfInfo;

import java.util.List;

public interface CommonService {
    public List<Semester> getListSemester();
    public List<ProfInfo> getListProf(Integer idFalcuty);
}
