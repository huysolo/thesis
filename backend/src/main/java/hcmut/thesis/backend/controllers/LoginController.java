/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.models.User;
import hcmut.thesis.backend.services.LoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MinBui
 */

@Controller
@CrossOrigin
public class LoginController {
    @Autowired
    LoginService loginService;
    
    @RequestMapping( value = "/demo", method = RequestMethod.POST)
    @ResponseBody
    public String doEdit(@RequestBody String t){
       System.out.println(t);
        return null;
    }
    
    @RequestMapping( value = "/demo1")
    @ResponseBody
    public String doEdit1(){
       String t = TokenAuth.addAuthentication("MinBui123");
       TokenAuth.getAuthentication(t);
        return "MinBui";
    }
}


