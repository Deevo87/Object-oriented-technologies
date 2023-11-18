package pl.edu.agh.to.school.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.to.school.controller.CourseController;
import pl.edu.agh.to.school.model.Course;
import pl.edu.agh.to.school.model.Grade;
import pl.edu.agh.to.school.model.Student;
import pl.edu.agh.to.school.repository.CourseRepository;
import pl.edu.agh.to.school.repository.GradeRepository;
import pl.edu.agh.to.school.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfigurator {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            if (studentRepository.count() == 0) {
                Student kowalski = new Student("Jan", "Kowalski", LocalDate.now(), "123456");
                Student student2 = new Student("Rafał", "Laskowski", LocalDate.now(), "654321");

                Course to2 = new Course("TO2");
                Course psi = new Course("PSI");

                Grade gradeTo2 = new Grade(5.0, to2);
                Grade gradeTo22 = new Grade(4.0, to2);
                Grade gradePSI = new Grade(4.5, psi);
                Grade gradePSI2 = new Grade(5.0, psi);

                courseRepository.save(to2);
                courseRepository.save(psi);

                studentRepository.save(kowalski);
                studentRepository.save(student2);

                gradeRepository.save(gradeTo2);
                gradeRepository.save(gradeTo22);
                gradeRepository.save(gradePSI);
                gradeRepository.save(gradePSI2);

                to2.assignStudent(student2);
                to2.assignStudent(kowalski);
                psi.assignStudent(kowalski);

                kowalski.giveGrade(gradeTo22);
                kowalski.giveGrade(gradePSI);
                student2.giveGrade(gradeTo2);
                student2.giveGrade(gradePSI2);

                gradeRepository.save(gradeTo2);
                gradeRepository.save(gradeTo22);
                gradeRepository.save(gradePSI);
                gradeRepository.save(gradePSI2);

                courseRepository.save(to2);
                courseRepository.save(psi);

                studentRepository.save(kowalski);
                studentRepository.save(student2);
            }
        };
    }
}
