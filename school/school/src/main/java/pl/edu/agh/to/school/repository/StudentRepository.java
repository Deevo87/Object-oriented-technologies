package pl.edu.agh.to.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to.school.model.Course;
import pl.edu.agh.to.school.model.Grade;
import pl.edu.agh.to.school.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByIndexNumber(String indexNumber);

}
