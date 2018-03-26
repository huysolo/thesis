package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TopicRepo extends JpaRepository<Topic, Integer> {
}

