package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.dtos.SimpleCourseEditionDto;
import org.generation.italy.legion.model.entities.CourseEdition;
import org.generation.italy.legion.model.services.abstractions.AbstractCourseDidacticService;
import org.generation.italy.legion.model.services.abstractions.AbstractCrudDidacticService;
import org.generation.italy.legion.model.services.implementations.StandardDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/editions")
public class ApiCourseEditionController {
   private StandardDidacticService service;
   @Autowired
   public ApiCourseEditionController(StandardDidacticService service){
      this.service = service;
   }

   @GetMapping("/{courseId}")
   public ResponseEntity<Iterable<SimpleCourseEditionDto>>findByCourseId(@PathVariable long courseId){
      Iterable<CourseEdition> iCe = service.findByCourseId(courseId);
      return ResponseEntity.ok().body(SimpleCourseEditionDto.fromEntityIterable(iCe));
   }

   @GetMapping()
   public ResponseEntity<Iterable<SimpleCourseEditionDto>> findAllCourseEdition(){
      Iterable<CourseEdition> iCe = service.findAllCourseEdition();
      return ResponseEntity.ok().body(SimpleCourseEditionDto.fromEntityIterable(iCe));
   }


}
