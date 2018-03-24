package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.Professor;
import hcmut.thesis.backend.models.Semester;
import hcmut.thesis.backend.modelview.ProfInfo;
import hcmut.thesis.backend.repositories.ProfessorRepo;
import hcmut.thesis.backend.repositories.SemesterRepo;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    private final SemesterRepo semesterRepo;

    private final ProfessorRepo professorRepo;

    private final UserRepo userRepo;

    @Autowired
    public CommonServiceImpl(SemesterRepo semesterRepo, ProfessorRepo professorRepo, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.semesterRepo = semesterRepo;
        this.professorRepo = professorRepo;
    }

    @Override
    public List<Semester> getListSemester() {
        return this.semesterRepo.findAll();
    }

    @Override
    public List<ProfInfo> getListProf(Integer idFalcuty) {
        List<ProfInfo> result = new ArrayList<>();

//        userRepo.getAllByIdFalcuty(idFalcuty).forEach(user -> {
//
//        });
        return null;
    }
}
