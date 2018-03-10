package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepo extends JpaRepository<Topic, Integer> {
}
