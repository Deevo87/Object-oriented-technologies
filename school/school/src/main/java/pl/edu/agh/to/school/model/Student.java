package pl.edu.agh.to.school.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String indexNumber;

    @OneToMany
    private List<Grade> grades;

    public Student() {
        this.grades = new ArrayList<>();
    }

    public Student(String firstName, String lastName, LocalDate birthDate, String indexNumber) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.indexNumber = indexNumber;
    }

    public void giveGrade(Grade grade) {
        this.grades.add(grade);
    }

    public List<Grade> getGrades() {
        return this.grades;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
