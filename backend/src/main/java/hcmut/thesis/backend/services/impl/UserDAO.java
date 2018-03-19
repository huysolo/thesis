/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.services.IUserDAO;
import hcmut.thesis.backend.models.Student;
import hcmut.thesis.backend.models.User;
import hcmut.thesis.backend.repositories.StudentRepo;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.services.LoginService;
import hcmut.thesis.subjects.CurrUserInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    LoginService loginService;

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
    public CurrUserInfo getCurrUserInfo(String username, String password) {
        CurrUserInfo currUserInfo = new CurrUserInfo();
        User user = this.getUser(username, password);
        if (user != null) {
            Boolean isStudent = this.checkUser((int) user.getIdUser());

            String isStd = (isStudent) ? "true" : "false";

            String token = loginService.createJWT(user.getUserName(), isStd);

            currUserInfo.setUsername(user.getUserName());
            currUserInfo.setToken(token);
            currUserInfo.setIsStudent(isStudent);
            return currUserInfo;
        }
        return null;
    }
}
