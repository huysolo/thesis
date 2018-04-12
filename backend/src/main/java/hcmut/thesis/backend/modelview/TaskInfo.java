/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.modelview;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author MinBui
 */
public class TaskInfo {
    public String title;
    public String description;
    public Timestamp deadline;
    public List<StudentDoTask> student; 
    
    public String getTitle(){
        return this.title;
    }
    public String getDescription(){
        return this.description;
    }
    public List<StudentDoTask> getStudent(){
        return this.student;
    }
    public Timestamp getDeadline(){
        return this.deadline;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setStudent(List<StudentDoTask> student){
        this.student = student;
    }
    public void setDeadline(Timestamp deadline){
        this.deadline = deadline;
    }
    
}
