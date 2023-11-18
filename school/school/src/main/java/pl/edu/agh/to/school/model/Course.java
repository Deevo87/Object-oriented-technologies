package pl.edu.agh.to.school.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private int id;

    private String courseName;

    @ManyToMany
    @JoinTable(
            name = "studentsCourses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> studentList;

    public Course() {
        this.studentList = new ArrayList<>();
    }

    public Course(String courseName) {
        this();
        this.courseName = courseName;
    }

    public void assignStudent(Student student) {
        this.studentList.add(student);
    }

    public void removeStudent(Student student) {
        this.studentList.remove(student);
    }

    public List<Student> getStudentList() {
        return this.studentList;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
