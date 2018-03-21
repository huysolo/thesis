package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepo extends JpaRepository<Semester, Integer> {
}
