package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.dtos.SimpleTeacherDto;
import org.generation.italy.legion.dtos.TeacherDto;
import org.generation.italy.legion.model.data.abstractions.GenericRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.legion.model.services.implementations.GenereicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/teachers") //davanti al prefisso dei metodi avremo "api"
public class ApiTeacherController {
    private AbstractDidacticService didacticService;
//    private AbstractCrudService<Teacher> teacherService;
    private GenereicService<Teacher> crudService;

    @Autowired
    public ApiTeacherController(AbstractDidacticService didacticService, GenericRepository<Teacher> teacherRepo){
        this.didacticService = didacticService;
//        this.teacherService = teacherService;
        this.crudService = new GenereicService<>(teacherRepo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> findById(@PathVariable long id){
        try {
            Optional<Teacher> teacherOp = crudService.findById(id);
            if(teacherOp.isPresent()){
                return ResponseEntity.ok().body(TeacherDto.fromEntity(teacherOp.get()));
            }
            return ResponseEntity.notFound().build();
        } catch (DataException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    //dovrà tornare un teacher con solo id, nome e cognome, nome skill e livello a cui la possiede
    @GetMapping()
    public ResponseEntity<Iterable<SimpleTeacherDto>> findWithSkillAndLevel(@RequestParam(required = false) Long skillId,
                                                                            @RequestParam(required = false) Level level){
        try {
            Iterable<Teacher> teacherIt = didacticService.findWithSkillAndLevel(skillId, level);
            return ResponseEntity.ok().body(SimpleTeacherDto.fromEntityIterable(teacherIt, skillId));
        } catch (DataException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<TeacherDto> create(@RequestBody TeacherDto teacherDto){
        Teacher tE = teacherDto.toEntity();
        try {
            var teacherResult = this.crudService.create(tE);
            TeacherDto dtoResult = TeacherDto.fromEntity(teacherResult);
            return ResponseEntity.created(URI.create("/api/teacher/" + teacherResult.getId())).body(dtoResult);
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update (@RequestBody TeacherDto teacherDto,@PathVariable long id){
        if(teacherDto.getId() != id){
            return ResponseEntity.badRequest().build();
        }
        Teacher te = teacherDto.toEntity();
        try{
            this.crudService.update(te);
            return ResponseEntity.noContent().build();
        } catch (DataException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        try{
            this.crudService.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (DataException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/hi")
    ResponseEntity<String> message(){
        return ResponseEntity.ok("hi ciao");
    }
}
