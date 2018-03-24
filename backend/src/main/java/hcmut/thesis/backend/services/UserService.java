/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinBui
 */
@Service
public interface UserService {
    String createJWT(String username, String isStudent);
    Claims parseJWT(String jwt);
}
