/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.Professor;
import hcmut.thesis.backend.models.User;
import hcmut.thesis.backend.modelview.CurrUserInfo;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinBui
 */
public interface IUserDAO {
    User getUser(String username, String Password);
    Boolean checkUser(int user_id);
    CurrUserInfo getCurrUserInfo(String username, String password);
    Professor findProfByUserId(int id);
}
