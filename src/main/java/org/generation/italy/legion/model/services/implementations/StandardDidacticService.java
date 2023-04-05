package org.generation.italy.legion.model.services.implementations;

import org.generation.italy.legion.model.data.abstractions.CourseEditionRepository;
import org.generation.italy.legion.model.data.abstractions.CourseRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.entities.CourseEdition;
import org.generation.italy.legion.model.services.abstractions.AbstractCourseDidacticService;
import org.generation.italy.legion.model.services.abstractions.AbstractCourseEditionDidacticService;
import org.generation.italy.legion.model.services.abstractions.AbstractCrudDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StandardDidacticService implements AbstractCrudDidacticService<Course>, AbstractCourseDidacticService{
    //@Autowired
    private CourseRepository repo; // field injection = inietta sul campo
    private CourseEditionRepository editionRepo;
    @Autowired
    public StandardDidacticService(CourseRepository repo, CourseEditionRepository editionRepo) { // constructor injection = inietta sul costruttore (è opzionale l'annotazione)
        this.repo = repo;       //iniezione delle dipendenze (tecnica) -> inversione del controllo (design pattern) o inversione delle dipendenze
        this.editionRepo = editionRepo;
        System.out.println(this.repo.getClass().getName());
    }

    //    @Autowired
    public void setRepo(CourseRepository repo) { // setter injection
        this.repo = repo;
    }

    @Override
    public List<Course> findCoursesByTitleContains(String part) throws DataException {
        return repo.findByTitleContains(part);
    }

    @Override
    public boolean adjustActiveCourses(int numActive) throws DataException {
        //chiama il repository per scoprire quanti corsi attivi esistono
        //se i corsi attivi sono <= numActive ritorniamo false per segnalare
        //che non è stato necessario apportare alcuna modifica
        //altrimenti chiameremo un metodo sul repository che cancella gli
        //n corsi più vecchi
        int actives = repo.countActiveCourses();
        if (actives <= numActive) {
            return false;
        }
        repo.deactivateOldest(actives - numActive);
        return true;
    }

    @Override
    public List<Course> findAll() throws DataException {
        return repo.findAll();
    }

    @Override
    public Optional<Course> findById(long id) throws DataException {
        return repo.findById(id);
    }

    @Override
    public Course create(Course entity) throws DataException {
        return repo.create(entity);
    }

    @Override
    public void update(Course entity) throws EntityNotFoundException, DataException {
        repo.update(entity);
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException, DataException {
        repo.deleteById(id);
    }

    public List<Course> findByTitleAndStatus(String part, boolean isActive) {
        return repo.findByTitleAndStatus(part, isActive);
    }

    @Override
    public List<Course> findByTitleAndStatusAndMinEdition(String part, boolean isActive, int minEditions) {
        return repo.findByTitleAndStatusAndMinEdition(part, isActive, minEditions);
    }

    public Iterable<CourseEdition> findByCourseId(long courseId){
        return editionRepo.findByCourseId(courseId);
    }
    public Iterable<CourseEdition> findAllCourseEdition(){
        return editionRepo.findAll();
    }
}