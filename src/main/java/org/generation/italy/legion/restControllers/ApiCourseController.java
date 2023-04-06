package org.generation.italy.legion.restControllers;


import org.generation.italy.legion.dtos.SimpleCourseDto;
import org.generation.italy.legion.model.data.abstractions.GenericRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.legion.model.services.implementations.GenereicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/courses")
public class ApiCourseController {

   private AbstractDidacticService didacticService;
   //    private AbstractCrudService<Course> courseService;
   private GenereicService<Course> crudService;


   @Autowired
   public ApiCourseController(AbstractDidacticService service,
                              GenericRepository<Course> courseRepo){
      this.didacticService = service;
//        this.courseService = courseService;
      this.crudService = new GenereicService<>(courseRepo);
   }

   @GetMapping("/{id}")
   public ResponseEntity<SimpleCourseDto> findById(@PathVariable long id) {
      try {
         Optional<Course> oc = crudService.findById(id);
         if (oc.isPresent()) {
            return ResponseEntity.ok().body(SimpleCourseDto.fromEntity(oc.get()));
         }
         return ResponseEntity.notFound().build();

      } catch (DataException e) {
         e.printStackTrace();
         return ResponseEntity.internalServerError().build();
      }
   }

   @GetMapping()
   public ResponseEntity<Iterable<SimpleCourseDto>> genericsSearch(@RequestParam String titleLike,
                                                                   @RequestParam(required = false) Boolean isActive,
                                                                   @RequestParam(required = false) Integer minEditions) {
      try {
         List<Course> result = null;

         if (titleLike != null && isActive != null && isActive && minEditions != null) {
            result = didacticService.findByTitleAndStatusAndMinEdition(titleLike, isActive, minEditions);
         } else if (titleLike != null && isActive != null && isActive) {
            result = didacticService.findByTitleAndStatus(titleLike, isActive);
         } else if (titleLike != null) {
            result = didacticService.findCoursesByTitleContains(titleLike);
         } else {
            return ResponseEntity.badRequest().build();
         }

         //assert result != null;
         return ResponseEntity.ok().body(SimpleCourseDto.fromEntityIterable(result));
      } catch (DataException e) {
         e.printStackTrace();
         return ResponseEntity.internalServerError().build();
      }

   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteById(@PathVariable long id){
      try {
         Optional<Course> oCourse = crudService.findById(id);
         if (oCourse.isEmpty()) {
            return ResponseEntity.notFound().build();
         }
         crudService.deleteById(id);
         return ResponseEntity.noContent().build();
      } catch (DataException | EntityNotFoundException e) {
         throw new RuntimeException(e);
      }

   }

   @PostMapping("/course") //a modo suo funziona
   public ResponseEntity<SimpleCourseDto> create() {
      try {
         Course co= crudService.create(new Course(25000, "Corso prova di aggiunta","Descriviamo cose, ne facciamo altre",
               "Un programma che non programma", 15, true, LocalDate.now().minusMonths(5)));
         return ResponseEntity.ok().body(SimpleCourseDto.fromEntity(co));
      } catch (DataException e) {
         e.printStackTrace();
         return ResponseEntity.internalServerError().build();
      }


   }
}
