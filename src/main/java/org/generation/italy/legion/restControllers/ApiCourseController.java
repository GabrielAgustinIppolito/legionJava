package org.generation.italy.legion.restControllers;


import org.generation.italy.legion.dtos.SimpleCourseDto;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.services.abstractions.AbstractCourseDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/courses")
public class ApiCourseController {

    private AbstractCourseDidacticService service;

    @Autowired
    public ApiCourseController(AbstractCourseDidacticService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleCourseDto> findById(@PathVariable long id) {
        try {
            Optional<Course> oc = service.findById(id);
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
    public <T> ResponseEntity<Iterable<SimpleCourseDto>> genericsSearch(T... args) {
        try {
            List<Course> result = null;

            switch (args.length) {
                case 1:
                    result = service.findCoursesByTitleContains((String) args[0]);
                    break;
                case 2:
                    result = service.findByTitleAndStatus((String) args[0], (Boolean) args[1]);
                    break;
                case 3:
                    result = service.findByTitleAndStatusAndMinEdition((String) args[0], (Boolean) args[1], (Long) args[2]);
                    break;
            }

            //assert result != null;
            return ResponseEntity.ok().body(result.stream().map(SimpleCourseDto::fromEntity).toList());
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

}
