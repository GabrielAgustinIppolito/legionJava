package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.dtos.SimpleCourseEditionDto;
import org.generation.italy.legion.model.entities.CourseEdition;
import org.generation.italy.legion.model.services.abstractions.AbstractCrudService;
import org.generation.italy.legion.model.services.abstractions.AbstractDidacticService;
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
   private AbstractDidacticService didacticService;
   private AbstractCrudService<CourseEdition> editionService;
   @Autowired
   public ApiCourseEditionController(AbstractDidacticService service){
      this.didacticService = service;
   }

   @GetMapping("/{courseId}")
   public ResponseEntity<Iterable<SimpleCourseEditionDto>>findByCourseId(@PathVariable long courseId){
      Iterable<CourseEdition> iCe = didacticService.findByCourseId(courseId);
      return ResponseEntity.ok().body(SimpleCourseEditionDto.fromEntityIterable(iCe));
   }

   @GetMapping()
   public ResponseEntity<Iterable<SimpleCourseEditionDto>> findAllCourseEdition(){
      Iterable<CourseEdition> iCe = didacticService.findAllCourseEdition();
      return ResponseEntity.ok().body(SimpleCourseEditionDto.fromEntityIterable(iCe));
   }


}
