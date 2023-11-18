package pl.edu.agh.to.school.services;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.model.Course;
import pl.edu.agh.to.school.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseByID(int id) {
        return courseRepository.findById(id);
    }

}
