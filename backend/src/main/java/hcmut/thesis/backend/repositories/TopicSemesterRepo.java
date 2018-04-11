package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.TopicPerSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicSemesterRepo  extends JpaRepository<TopicPerSemester, Integer> {
    @Query("SELECT t.idTopic FROM TopicPerSemester t WHERE  t.semesterNo = :semesterNo")
    List<Integer> findTopBySemesterNo(@Param("semesterNo") Integer semesterNo);

    @Query("SELECT t.idTopicSemester FROM TopicPerSemester t WHERE  t.idTopic = :idTopic AND t.semesterNo = :semesterNo")
    List<Integer> findTopicPerSemesterByIdTopicAndAndSemesterNo(@Param("idTopic") Integer idTopic, @Param("semesterNo") Integer semesterNo);

}


