package org.generation.italy.legion.model.data.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
//DAO == Repository... Data Access Object

public interface AbstractCrudRepository<T>{
   List<T> findAll() throws DataException;
   Optional<T> findById(long id) throws DataException;
   T create(T entity) throws DataException;
   void update(T entity) throws EntityNotFoundException, DataException;
   void deleteById(long id) throws EntityNotFoundException, DataException;
}
