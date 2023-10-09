package pl.edu.agh.iisg.to.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import pl.edu.agh.iisg.to.executor.QueryExecutor;

public class Course {

    public static final String TABLE_NAME = "course";

    private static final Logger logger = Logger.getGlobal();

    private final int id;

    private final String name;

    private List<Student> enrolledStudents;

    private boolean isStudentsListDownloaded = false;

    Course(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static Optional<Course> create(final String name) {
        String insertSql = "INSERT INTO course (name) VALUES (?);";
        Object[] args = {
                name
        };

        try {
            int id = QueryExecutor.createAndObtainId(insertSql, args);
            return Course.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Optional<Course> findById(final int id) {
        String findByIdSql = "SELECT * FROM course WHERE id = ?";
        Object[] args = {
                id
        };

        try (ResultSet rs = QueryExecutor.read(findByIdSql, args)) {
            if (rs.next()) {
                return Optional.of(new Course(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean enrollStudent(final Student student) {
        String enrollStudentSql = "INSERT INTO student_course (student_id, course_id) VALUES (?, ?);";
        Object[] args = {
                student.id(),
                this.id
        };

        //TODO
        try {
            QueryExecutor.createAndObtainId(enrollStudentSql, args);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Student> studentList() {
        String findStudentListSql = "SELECT * FROM student_course as SC " +
                "INNER JOIN student S ON SC.student_id = S.id " +
                "WHERE course_id = (?);";
        Object[] args = {
                this.id
        };
        ResultSet resultSet = null;

        try {
            resultSet = QueryExecutor.read(findStudentListSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Student> resultList = new LinkedList<>();

        // TODO
        while (true) {
            try {
                if (!resultSet.next()) break;
                else {
                    int student_id = resultSet.getInt("student_id");
                    String name = resultSet.getString("first_name");
                    String lastname = resultSet.getString("last_name");
                    int index_number = resultSet.getInt("index_number");
                    Student newStudent = new Student(student_id, name, lastname, index_number);
                    resultList.add(newStudent);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }

    public List<Student> cachedStudentsList() {
        //TOTO implement
        return enrolledStudents;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public static class Columns {

        public static final String ID = "id";

        public static final String NAME = "name";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        return name.equals(course.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
