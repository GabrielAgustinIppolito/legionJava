package org.generation.italy.legion.model.services.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.entities.CourseEdition;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;

import java.util.List;
import java.util.Optional;

public interface AbstractDidacticService {

    List<Course> findCoursesByTitleContains(String part) throws DataException;

    boolean adjustActiveCourses(int numActive) throws DataException; //se corsi attivi > numActive disattiva i più vecchi

    List<Course> findByTitleAndStatus(String part, boolean isActive);

    List<Course> findByTitleAndStatusAndMinEdition(String part, boolean isActive, int minEditions);
    Iterable<Teacher> findWithCompetenceByLevel(Level teacherLevel) throws DataException;
    Iterable<Teacher> findWithSkillAndLevel(long idSkill, Level competenceLevel) throws DataException;
    Iterable<CourseEdition> findByCourseId(long courseId);

    Iterable<CourseEdition> findAllCourseEdition();

    Iterable<CourseEdition> findCourseEditionMedianByCost();
    Optional<Double> getCourseEditionCostMode();
}
