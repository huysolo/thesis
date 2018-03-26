/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.modelview;

/**
 *
 * @author MinBui
 */
public class CurrUserInfo {
    private String username;
    private int userID;
    private String token;
    private Boolean isStudent;
    private String firstname;
    private String lastname;
    private String email;
    private String photo;
    private String gender;
    private String degree;
    private String skills;
    private int profID;
    public String getUsername(){
        return this.username;
    }
    public int getUserID(){
        return this.userID;
    }
    public String getFistname(){
        return this.firstname;
    }
    public String getLastname(){
        return this.lastname;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhoto(){
        return this.photo;
    }
    public String getGender(){
        return this.gender;
    }
    public String getDegree(){
        return this.degree;
    }
    public String getSkills(){
        return this.skills;
    }
    public int getProfID(){
        return this.profID;
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
    public void setUserID(int userID){
        this.userID = userID;
    }
    public void setFirstname(String first_name){
        this.firstname = first_name;
    }
    public void setLastname(String last_name){
        this.lastname = last_name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhoto(String photo){
        this.photo = photo;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setDegree(String degree){
        this.degree = degree;
    }
    public void setSkills(String skills){
        this.skills = skills;
    }
    public void setProfID(int id){
        this.profID = id;
    }
    public void setToken(String token){
        this.token = token;
    }
    public void setIsStudent(boolean isStd){
        this.isStudent = isStd;
    }
}
