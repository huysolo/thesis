
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.StudentTopicSem;
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
public interface StudentTopicSemRepo extends JpaRepository<StudentTopicSem, Integer> {

    @Query("SELECT st FROM StudentTopicSem st WHERE st.idTopicSem = :idTopicSem")
    List<StudentTopicSem> getAllByIdTopicSem(@Param("idTopicSem") Integer idTopicSem);

    @Query("SELECT st FROM StudentTopicSem st WHERE st.idStudent = :idStudent")
    List<StudentTopicSem> getStudentTopicSemByIdStudent(@Param("idStudent") Integer idStudent);

    @Query("SELECT st FROM StudentTopicSem st WHERE st.idStudent = :idStudent AND st.idTopicSem = :idTopicSem")
    List<StudentTopicSem> getStudentTopicSemByAll(@Param("idStudent") Integer idStudent, @Param("idTopicSem") Integer idTopicSem);

}
