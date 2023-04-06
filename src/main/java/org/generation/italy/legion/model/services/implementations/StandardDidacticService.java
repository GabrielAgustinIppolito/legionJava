package org.generation.italy.legion.model.services.implementations;

import org.generation.italy.legion.model.data.abstractions.CourseEditionRepository;
import org.generation.italy.legion.model.data.abstractions.CourseRepository;
import org.generation.italy.legion.model.data.abstractions.TeacherRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.entities.CourseEdition;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StandardDidacticService implements AbstractDidacticService {
    //@Autowired
    private CourseRepository courseRepo; // field injection = inietta sul campo
    private TeacherRepository teacherRepo;
    private CourseEditionRepository editionRepo;
    @Autowired
    public StandardDidacticService(CourseRepository repo, CourseEditionRepository editionRepo, TeacherRepository teacherRepo) { // constructor injection = inietta sul costruttore (è opzionale l'annotazione)
        this.courseRepo = repo;       //iniezione delle dipendenze (tecnica) -> inversione del controllo (design pattern) o inversione delle dipendenze
        this.editionRepo = editionRepo;
        this.teacherRepo = teacherRepo;
    }

    //    @Autowired
    public void setCourseRepo(CourseRepository courseRepo) { // setter injection
        this.courseRepo = courseRepo;
    }

    @Override
    public List<Course> findCoursesByTitleContains(String part) throws DataException {
        return courseRepo.findByTitleContains(part);
    }

    @Override
    public boolean adjustActiveCourses(int numActive) throws DataException {
        //chiama il repository per scoprire quanti corsi attivi esistono
        //se i corsi attivi sono <= numActive ritorniamo false per segnalare
        //che non è stato necessario apportare alcuna modifica
        //altrimenti chiameremo un metodo sul repository che cancella gli
        //n corsi più vecchi
        int actives = courseRepo.countActiveCourses();
        if (actives <= numActive) {
            return false;
        }
        courseRepo.deactivateOldest(actives - numActive);
        return true;
    }

    public List<Course> findByTitleAndStatus(String part, boolean isActive) {
        return courseRepo.findByTitleAndStatus(part, isActive);
    }

    @Override
    public List<Course> findByTitleAndStatusAndMinEdition(String part, boolean isActive, int minEditions) {
        return courseRepo.findByTitleAndStatusAndMinEdition(part, isActive, minEditions);
    }

    @Override
    public Iterable<Teacher> findWithCompetenceByLevel(Level teacherLevel) throws DataException {
        return null;
    }

    @Override
    public Iterable<Teacher> findWithSkillAndLevel(long idSkill, Level competenceLevel) throws DataException {
        return null;
    }
    @Override
    public Iterable<CourseEdition> findByCourseId(long courseId){
        return editionRepo.findByCourseId(courseId);
    }
    @Override
    public Iterable<CourseEdition> findAllCourseEdition(){
        return editionRepo.findAll();
    }

    @Override
    public Iterable<CourseEdition> findCourseEditionMedianByCost() {
        return editionRepo.findMedian();
    }
    @Override
    public Optional<Double> getCourseEditionCostMode(){
        return editionRepo.getCourseEditionCostMode();
    }
}
