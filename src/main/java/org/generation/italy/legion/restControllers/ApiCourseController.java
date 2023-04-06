package org.generation.italy.legion.restControllers;


import org.generation.italy.legion.dtos.SimpleCourseDto;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.services.abstractions.AbstractCrudService;
import org.generation.italy.legion.model.services.abstractions.AbstractDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/courses")
public class ApiCourseController {

    private AbstractDidacticService didacticService;
    private AbstractCrudService<Course> courseService;

    @Autowired
    public ApiCourseController(AbstractDidacticService service, AbstractCrudService<Course> courseService){
        this.didacticService = service;
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleCourseDto> findById(@PathVariable long id) {
        try {
            Optional<Course> oc = courseService.findById(id);
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

}
