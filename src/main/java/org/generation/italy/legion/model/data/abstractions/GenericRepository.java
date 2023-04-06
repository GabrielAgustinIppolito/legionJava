package org.generation.italy.legion.model.data.abstractions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean //dice a spring di non fare le implementazioni di JPARepository
public interface GenericRepository<T> extends JpaRepository<T, Long> {

}
