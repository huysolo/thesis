package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.Semester;
import hcmut.thesis.backend.modelview.ProfInfo;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.repositories.ProfessorRepo;
import hcmut.thesis.backend.repositories.SemesterRepo;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.services.CommonService;
import hcmut.thesis.backend.services.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private SemesterRepo semesterRepo;
    @Autowired
    private ProfessorRepo professorRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserSession userSession;
    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<Semester> getListSemester() {
        return this.semesterRepo.findAll();
    }

    @Override
    public List<ProfInfo> getListProf() {
        List<ProfInfo> result = new ArrayList<>();
        Integer idFalcuty = userDAO.findUserByUserId(userSession.getUserID()).getIdFalcuty();
        userRepo.getAllByIdFalcuty(idFalcuty).forEach(user -> {
            ProfInfo profInfo = new ProfInfo();

            profInfo.setProfessor(userDAO.findProfByUserId(user.getIdUser()));
            profInfo.setName(getFullName(user.getFirstName(), user.getLastName()));
            result.add(profInfo);
        });
        return result;
    }

    @Override
    public String getFullName(String fNamme, String lname) {
        return fNamme + " " + lname;    }


}
