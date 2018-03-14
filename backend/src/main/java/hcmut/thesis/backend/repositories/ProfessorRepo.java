/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author MinBui
 */
public interface ProfessorRepo extends JpaRepository<Professor, Integer> {
    
}
