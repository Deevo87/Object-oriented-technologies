package pl.edu.agh.to.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.to.school.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
