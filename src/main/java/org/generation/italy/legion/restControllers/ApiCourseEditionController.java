package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.dtos.SimpleCourseEditionDto;
import org.generation.italy.legion.model.data.abstractions.GenericRepository;
import org.generation.italy.legion.model.entities.CourseEdition;
import org.generation.italy.legion.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.legion.model.services.implementations.GenereicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/editions")
public class ApiCourseEditionController {
   private AbstractDidacticService didacticService;
//   private AbstractCrudService<CourseEdition> editionService;
   private GenereicService<CourseEdition> crudService;

   @Autowired
   public ApiCourseEditionController(AbstractDidacticService service, GenericRepository<CourseEdition> courseEditionRepo){
      this.didacticService = service;
      this.crudService = new GenereicService<>(courseEditionRepo);

   }

   @GetMapping("/{courseId}")
   public ResponseEntity<Iterable<SimpleCourseEditionDto>>findByCourseId(@PathVariable long courseId){
      Iterable<CourseEdition> iCe = didacticService.findByCourseId(courseId);
      return ResponseEntity.ok().body(SimpleCourseEditionDto.fromEntityIterable(iCe));
   }

   @GetMapping()
   public ResponseEntity<Iterable<SimpleCourseEditionDto>> findAllCourseEdition(@RequestParam(required = false)
                                                                                Boolean findMedian){
      Iterable<CourseEdition> iCe = null;
      if(findMedian != null && findMedian){
         iCe = didacticService.findCourseEditionMedianByCost();
      } else{
         iCe = didacticService.findAllCourseEdition();
      }
      return ResponseEntity.ok().body(SimpleCourseEditionDto.fromEntityIterable(iCe));
   }
   @GetMapping("/cost/mode")
   public ResponseEntity<Double> getCourseEditionCostMode(){
      Optional<Double> od =didacticService.getCourseEditionCostMode();
      return od.map(aDouble -> ResponseEntity.ok().body(aDouble)).orElseGet(() -> ResponseEntity.notFound().build());
   }


}
