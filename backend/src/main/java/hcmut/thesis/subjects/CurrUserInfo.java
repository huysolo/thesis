/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.subjects;

/**
 *
 * @author MinBui
 */
public class CurrUserInfo {
    String username;
    String token;
    Boolean isStudent;
    public String getUsername(){
        return this.username;
    }
    public String getToken(){
        return this.token;
    }
    public Boolean getIsStudent(){
        return this.isStudent;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    public void setToken(String token){
        this.token = token;
    }
    public void setIsStudent(boolean isStd){
        this.isStudent = isStd;
    }
}
