package pl.edu.agh.to.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.to.school.model.Course;
import pl.edu.agh.to.school.model.Grade;
import pl.edu.agh.to.school.model.Student;
import pl.edu.agh.to.school.services.CourseService;
import pl.edu.agh.to.school.services.GradeService;
import pl.edu.agh.to.school.services.StudentService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "students")
@Transactional
public class StudentController {

    private final StudentService studentService;

    private final CourseService courseService;

    private final GradeService gradeService;

    public StudentController(StudentService studentService, CourseService courseService, GradeService gradeService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/student")
    @ResponseBody
    public Student getStudentByIndex(@RequestParam(required = false) String indexNumber) {
        System.out.println(indexNumber);
        return studentService.getStudentByIndex(indexNumber);
    }

    @PostMapping("/grade")
    public ResponseEntity<String> gradeStudent(@RequestParam String indexNumber, @RequestParam double gradeValue, @RequestParam String courseId) {
        Student student = studentService.getStudentByIndex(indexNumber);
        Optional<Course> potentialCourse = courseService.getCourseByID(Integer.parseInt(courseId));
        if (potentialCourse.isPresent()) {
            Course course = potentialCourse.get();
            Grade newGrade = new Grade(gradeValue, course);
            gradeService.saveGrade(newGrade);
            student.giveGrade(newGrade);
            studentService.saveStudent(student);
            return ResponseEntity.ok("Student został poprawnie oceniony!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kurs o podanym ID nie został znaleziony.");
        }
    }
}

