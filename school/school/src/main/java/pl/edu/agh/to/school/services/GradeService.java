package pl.edu.agh.to.school.services;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.model.Grade;
import pl.edu.agh.to.school.repository.GradeRepository;

@Service
public class GradeService {

    public final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public void saveGrade(Grade grade) {
        gradeRepository.save(grade);
    }
}
