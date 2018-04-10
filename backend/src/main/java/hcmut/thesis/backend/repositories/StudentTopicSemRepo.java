package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.StudentTopicSem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentTopicSemRepo extends JpaRepository<StudentTopicSem, Integer> {
    @Query("SELECT st FROM StudentTopicSem st WHERE st.idTopicSem = :idTopicSem")
    List<StudentTopicSem> getAllByIdTopicSem(@Param("idTopicSem") Integer idTopicSem);
}
