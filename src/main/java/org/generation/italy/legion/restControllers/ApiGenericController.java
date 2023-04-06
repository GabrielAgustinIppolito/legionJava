package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.model.data.abstractions.GenericRepository;
import org.generation.italy.legion.model.services.implementations.GenereicService;

public class ApiGenericController<T> {
   private GenereicService<T> service;

   public ApiGenericController(GenericRepository<T> repo) {
      this.service = new GenereicService<T>(repo);
   }

//   @GetMapping()
//   public ResponseEntity<Iterable<T>> findALl(){
//      return service.findAll();
//   }
}
