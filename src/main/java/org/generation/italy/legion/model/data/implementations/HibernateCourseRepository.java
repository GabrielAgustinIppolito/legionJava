//package org.generation.italy.legion.model.data.implementations;
//
//import jakarta.persistence.EntityManager;
//import org.generation.italy.legion.model.data.abstractions.CourseRepository;
//import org.generation.italy.legion.model.data.exceptions.DataException;
//import org.generation.italy.legion.model.entities.Course;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.FluentQuery;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//import static org.generation.italy.legion.model.data.HibernateConstants.*;
//
//@Repository
//@Profile("hibernate")
//public class HibernateCourseRepository extends GenericCrudRepository<Course> implements CourseRepository {
////    @Autowired
////    private Session session;
////    @Override
////    public List<Course> findAll() throws DataException {
////        Query<Course> q = session.createQuery("from Course", Course.class); //utilizza query di Hibernate
////        return q.list();
////    }
////
////    @Override
////    public Optional<Course> findById(long id) throws DataException {
////        Course found = session.get(Course.class,id);
////        return found!=null ? Optional.of(found) : Optional.empty();
////    }
////
////    @Override
////    public Course create(Course course) throws DataException {
////        session.persist(course);
////        return course;
////    }
////
////    @Override
////    public void update(Course course) throws EntityNotFoundException, DataException {
////        session.merge(course);
////    }
////
////    @Override
////    public void deleteById(long id) throws EntityNotFoundException, DataException {
////        Course c = session.getReference(Course.class,id);
////        session.remove(c);
////    }
////
////
////
////
////
////    public HibernateCourseRepository(Session s){
////        super(s, Course.class);
////    }
//    @Autowired
//    public HibernateCourseRepository(EntityManager em){
//        super(em, Course.class);
//    }
//    @Override
//    public int countActiveCourses() throws DataException {
//        Query<Integer> q = session.createQuery("select count (*) from Course where isActive = true ", Integer.class);
//        int n = q.getSingleResult();
//        return n;
//    }
//
//    @Override
//    public void deactivateOldest(int n) throws DataException {
////       MutationQuery q = session.createMutationQuery(HQL_DEACTIVATE_OLDEST_N_COURSES);
////       q.setParameter("limit", n);
////       q.executeUpdate();
//
//       Query<Course> q = session.createQuery(HQL_OLDEST_N_COURSES, Course.class).setParameter("limit", n);
//       List<Course> lc = q.list();
//       lc.forEach(Course::deactivate);
//
//    }
//
//    @Override
//    public boolean adjustActiveCourses(int NumActive) throws DataException {
//        return false;
//    }
//
//    @Override
//    public List<Course> findByTitleAndStatus(String part, boolean isActive) {
//        Query<Course> q = session.createQuery(HQL_FIND_COURSE_BY_TITLE_AND_STATUS, Course.class);
//        q.setParameter("part", "%" + part + "%");
//        q.setParameter("status", isActive);
//        return q.list();
//    }
//
//    @Override
//    public List<Course> findByTitleAndStatusAndMinEdition(String part, boolean isActive, int minEditions) {
//        Query<Course> q = session.createQuery(HQL_FIND_COURSE_BY_TITLE_STATUS_EDITIONS, Course.class);
//        q.setParameter("part", "%" + part + "%");
//        q.setParameter("status", isActive);
//        q.setParameter("editions", minEditions);
//        return q.list();
//    }
//
//    @Override
//    public List<Course> findByTitleContains(String part) throws DataException {
//        Query<Course> q = session.createQuery(HQL_FIND_COURSE_BY_TITLE_CONTAINS, Course.class);
//        q.setParameter("p", "%" + part + "%");
//        return q.list();
//    }
//
//    @Override
//    public void flush() {
//
//    }
//
//    @Override
//    public <S extends Course> S saveAndFlush(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends Course> List<S> saveAllAndFlush(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public void deleteInBatch(Iterable<Course> entities) {
//        CourseRepository.super.deleteInBatch(entities);
//    }
//
//    @Override
//    public void deleteAllInBatch(Iterable<Course> entities) {
//
//    }
//
//    @Override
//    public void deleteAllByIdInBatch(Iterable<Long> longs) {
//
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//
//    }
//
//    @Override
//    public Course getOne(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public Course getById(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public Course getReferenceById(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public <S extends Course> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends Course> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends Course> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends Course> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends Course> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends Course> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    public <S extends Course, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//        return null;
//    }
//
//    @Override
//    public <S extends Course> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends Course> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<Course> findById(Long aLong) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Long aLong) {
//        return false;
//    }
//
//    @Override
//    public List<Course> findAllById(Iterable<Long> longs) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//
//    }
//
//    @Override
//    public void delete(Course entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends Long> longs) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Course> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public List<Course> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<Course> findAll(Pageable pageable) {
//        return null;
//    }
//}