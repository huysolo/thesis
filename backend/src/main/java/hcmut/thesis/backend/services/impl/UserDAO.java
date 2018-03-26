/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.services.IUserDAO;
import hcmut.thesis.backend.models.Student;
import hcmut.thesis.backend.models.Professor;
import hcmut.thesis.backend.models.User;
import hcmut.thesis.backend.repositories.StudentRepo;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.modelview.CurrUserInfo;
import hcmut.thesis.backend.repositories.ProfessorRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import hcmut.thesis.backend.services.UserService;

/**
 *
 * @author MinBui
 */
@Component
public class UserDAO implements IUserDAO {

    @Autowired
    UserRepo userRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    UserService loginService;
    
    @Autowired
    ProfessorRepo profRepo;

    @Override
    public User getUser(String username, String password) {
        List<User> listUser = userRepo.findAll();
        for (int i = 0; i < listUser.size(); i++) {
            if ((listUser.get(i).getUserName() == null ? username == null : listUser.get(i).getUserName().equals(username))
                    && (listUser.get(i).getPassword() == null ? password == null : listUser.get(i).getPassword().equals(password))) {
                return listUser.get(i);
            }
        }
        return null;
    }

    @Override
    public Boolean checkUser(int user_id) {
        List<Student> listStudent = studentRepo.findAll();
        for (int i = 0; i < listStudent.size(); i++) {
            if (listStudent.get(i).getIdUser() == user_id) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Professor findProfByUserId(int id){
        List<Professor> listProf = profRepo.findAll();
        for(int i = 0; i< listProf.size(); i++){
            if(listProf.get(i).getIdUser() == id){
                return listProf.get(i);
            }
        }
        return null;
    }

    @Override
    public CurrUserInfo getCurrUserInfo(String username, String password) {
        CurrUserInfo currUserInfo = new CurrUserInfo();
        User user = this.getUser(username, password);
        if (user != null) {
            Boolean isStudent = this.checkUser((int) user.getIdUser());
            if(!isStudent){
                Professor prof = this.findProfByUserId(user.getIdUser());
                currUserInfo.setProfID(prof.getIdProfessor());
                currUserInfo.setDegree(prof.getDegree());
                currUserInfo.setSkills(prof.getSkills());
                
            }

            String isStd = (isStudent) ? "true" : "false";

            String token = loginService.createJWT(user.getUserName(), isStd);

            currUserInfo.setUsername(user.getUserName());
            currUserInfo.setUserID(user.getIdUser());
            currUserInfo.setFirstname(user.getFirstName());
            currUserInfo.setLastname(user.getLastName());
            currUserInfo.setEmail(user.getEmail());
            currUserInfo.setGender(user.getGender());
            currUserInfo.setPhoto(user.getPhoto());
            
            currUserInfo.setToken(token);
            currUserInfo.setIsStudent(isStudent);
            return currUserInfo;
        }
        return null;
    }
}
