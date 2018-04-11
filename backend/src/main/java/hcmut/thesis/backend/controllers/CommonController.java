package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.models.Semester;
import hcmut.thesis.backend.models.StudentTopicSem;
import hcmut.thesis.backend.modelview.ProfInfo;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CommonController {
    @Autowired
    CommonService commonService;
    @Autowired
    UserSession userSession;
    
     @Autowired
    StudentTopicSemRepo stdTopicSemRepo;

    @RequestMapping(value = "listSemester", method = RequestMethod.GET)
    List<Semester> getListSemster(){
        return commonService.getListSemester();
    }

    @RequestMapping(value = "listProf", method = RequestMethod.GET)
    List<ProfInfo> getListProf(){
        return commonService.getListProf();

    }
    
    @RequestMapping(value = "aaa", method = RequestMethod.GET)
    List<StudentTopicSem> getaaa(@RequestParam("id") Integer id) {
        return stdTopicSemRepo.getAllByIdTopicSem(id);
    }
}
