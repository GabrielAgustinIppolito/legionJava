package org.generation.italy.legion.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.generation.italy.legion.model.entities.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class SimpleCourseEditionDto{
   private long id;
   private double cost;

   public SimpleCourseEditionDto(long id, double cost) {
      this.id = id;
      this.cost = cost;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }


   public double getCost() {
      return cost;
   }

   public void setCost(double cost) {
      this.cost = cost;
   }

   public static SimpleCourseEditionDto fromEntity(CourseEdition ce){
      return new SimpleCourseEditionDto(ce.getId(), ce.getCost());
   }
   public static Iterable<SimpleCourseEditionDto> fromEntityIterable(Iterable<CourseEdition> iCe){
      return StreamSupport.stream(iCe.spliterator(), false)
            .map(SimpleCourseEditionDto::fromEntity)
            .toList();
   }

}