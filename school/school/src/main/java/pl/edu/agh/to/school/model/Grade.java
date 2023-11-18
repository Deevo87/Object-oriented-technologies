package pl.edu.agh.to.school.model;

import javax.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue
    private int id;

    private double gradeValue;

    @ManyToOne
    private Course course;

    public Grade() {};

    public Grade(double gradeValue, Course course) {
        this.gradeValue = gradeValue;
        this.course = course;
    }

    public double getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(float gradeValue) {
        this.gradeValue = gradeValue;
    }
}
