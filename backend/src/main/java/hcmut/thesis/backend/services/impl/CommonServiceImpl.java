package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.Semester;
import hcmut.thesis.backend.repositories.SemesterRepo;
import hcmut.thesis.backend.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    SemesterRepo semesterRepo;
    @Override
    public List<Semester> getListSemester() {
        return this.semesterRepo.findAll();
    }
}
