package pl.edu.agh.to.school.controller;


import org.springframework.web.bind.annotation.*;
import pl.edu.agh.to.school.model.Course;
import pl.edu.agh.to.school.model.Student;
import pl.edu.agh.to.school.services.CourseService;

import java.util.List;

@RestController
@RequestMapping(path = "courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses() {
       return courseService.getCourses();
    }

    @GetMapping("/participants/{id}")
    @ResponseBody
    public List<Student> getParticipants(@PathVariable String id) {
        System.out.println(courseService.getCourseByID(Integer.parseInt(id)).get().getStudentList());
        return courseService.getCourseByID(Integer.parseInt(id)).get().getStudentList();
    }
}
