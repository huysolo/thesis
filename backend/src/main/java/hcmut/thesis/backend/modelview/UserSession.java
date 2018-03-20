/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.modelview;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author MinBui
 */
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class UserSession {
    private String username;
    private Boolean isStudent;
    private Boolean isProf;
    
    public String getUsername(){
        return this.username;
    }
    public Boolean isStudent(){
        return this.isStudent;
    }
    public Boolean isProf(){
        return this.isProf;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setIsStudent(Boolean isStudent){
        this.isStudent = isStudent;
    }
    public void setIsProf(Boolean isProf){
        this.isProf = isProf;
    }
}
