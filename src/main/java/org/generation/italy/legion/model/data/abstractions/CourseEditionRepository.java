package org.generation.italy.legion.model.data.abstractions;

import org.generation.italy.legion.model.entities.CourseEdition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseEditionRepository extends JpaRepository<CourseEdition,Long> {

//    double getTotalCost();
////    @Query(value = "SELECT u FROM User u")
//    Optional<CourseEdition> findMostExpensive();
//
//    double findAverageCost();
//    Iterable<Double> findAllDuration();
    Iterable<CourseEdition> findByCourseId(long courseId);
//    Iterable<CourseEdition> findByCourseTitleAndPeriod(String titlePart,
//                                                       LocalDate startAt, LocalDate endAt);
//    Iterable<CourseEdition> findMedian();
//    Optional<Double> getCourseEditionCostMode();



}