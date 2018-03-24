/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author MinBui
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
    @Query("SELECT u FROM User u WHERE u.idFalcuty = :idFalcuty")
    List<User> getAllByIdFalcuty(@Param("idFalcuty") Integer idFalcuty);

    //public User findByLoginInfo(String user_name, String password);
}

