package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.models.Professor;
import hcmut.thesis.backend.models.Semester;
import hcmut.thesis.backend.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CommonController {
    @Autowired
    CommonService commonService;

    @RequestMapping(value = "listSemester", method = RequestMethod.GET)
    List<Semester> getListSemster(){
        return commonService.getListSemester();
    }

    @RequestMapping(value = "listProf", method = RequestMethod.GET)
    List<Professor> getListProf(@RequestParam(value = "idFal", required = true) Integer idFalcuty ){
//        return commonService.getListProf(idFalcuty);
        return null;
    }
}
