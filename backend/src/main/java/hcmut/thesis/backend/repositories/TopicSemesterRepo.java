package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.TopicPerSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicSemesterRepo  extends JpaRepository<TopicPerSemester, Integer> {
    @Query("SELECT t.idTopic FROM TopicPerSemester t WHERE  t.semesterNo = :semesterNo")
    Integer findTopBySemesterNo(@Param("semesterNo") Integer semesterNo);
}
