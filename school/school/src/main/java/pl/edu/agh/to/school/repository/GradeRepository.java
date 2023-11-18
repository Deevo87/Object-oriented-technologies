package pl.edu.agh.to.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.to.school.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
}
