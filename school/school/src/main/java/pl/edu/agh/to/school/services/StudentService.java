package pl.edu.agh.to.school.services;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.model.Grade;
import pl.edu.agh.to.school.model.Student;
import pl.edu.agh.to.school.repository.StudentRepository;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByIndex(String indexNumber) {
        return studentRepository.findByIndexNumber(indexNumber);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public double calculatingMeanForStudent(Student student) {
        double summed = student.getGrades().stream().mapToDouble(Grade::getGradeValue).sum();
        return summed / student.getGrades().size();
    }
}



