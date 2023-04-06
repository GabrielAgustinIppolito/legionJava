package org.generation.italy.legion.model.services.implementations;

import org.generation.italy.legion.model.data.abstractions.GenericRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.data.implementations.GenericCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class GenereicService<T> {
   private GenericRepository<T> repo;

   public GenereicService(GenericRepository<T> repo) {
      this.repo = repo;
   }

   public void setRepo(GenericRepository repo) {
      this.repo = repo;
   }


   public List<T> findAll() throws DataException {
      return repo.findAll();
   }


   public Optional<T> findById(long id) throws DataException {
      return repo.findById(id);
   }


   @Transactional
   public T create(T entity) throws DataException {
      return repo.save(entity);
   }

   @Transactional
   public void update(T entity) throws EntityNotFoundException, DataException {
      repo.save(entity);
   }

   @Transactional
   public void deleteById(long id) throws EntityNotFoundException, DataException {
      repo.deleteById(id);
   }
}



