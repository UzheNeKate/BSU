package com.example.lab5_ultimate.model.dao;

import com.example.lab5_ultimate.model.entity.ReaderEntity;
import com.example.lab5_ultimate.model.entity.ReaderEntity_;
import com.example.lab5_ultimate.model.exception.DaoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.*;

import java.util.List;

public class ReaderDao extends AbstractDao implements Dao<ReaderEntity> {
    /**
     * Constructor
     */
    public ReaderDao() {
        super();
    }

    /**
     * @param name name of reader
     * @return reader by the name
     * @throws DaoException exception
     */
    public ReaderEntity get(String name) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ReaderEntity r;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ReaderEntity> criteriaQuery = criteriaBuilder.createQuery(ReaderEntity.class);
            Root<ReaderEntity> rootReader = criteriaQuery.from(ReaderEntity.class);
            Predicate condition = criteriaBuilder.equal(rootReader.get(ReaderEntity_.name), name);
            criteriaQuery.where(condition);

            r = entityManager.createQuery(criteriaQuery).getSingleResult();
            logger.info("Get reader by name");
        } catch(PersistenceException ex){
            throw new DaoException("Cannot get reader with name = " + name);
        } finally {
            entityManager.close();
        }
        return r;
    }

    /**
     * @param id id of reader
     * @return reader by id
     * @throws DaoException exception
     */
    @Override
    public ReaderEntity get(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ReaderEntity r;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ReaderEntity> criteriaQuery = criteriaBuilder.createQuery(ReaderEntity.class);
            Root<ReaderEntity> rootReader = criteriaQuery.from(ReaderEntity.class);
            Predicate condition = criteriaBuilder.equal(rootReader.get(ReaderEntity_.id), id);
            criteriaQuery.where(condition);

            r = entityManager.createQuery(criteriaQuery).getSingleResult();
            logger.info("Get reader by id");
        } catch(PersistenceException ex){
            throw new DaoException("Cannot get reader with id = " + id);
        } finally {
            entityManager.close();
        }
        return r;
    }

    /**
     * @return list of all readers
     * @throws DaoException exception
     */
    @Override
    public List<ReaderEntity> getAll() throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<ReaderEntity> readers;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ReaderEntity> criteriaQuery = criteriaBuilder.createQuery(ReaderEntity.class);
            Root<ReaderEntity> reader = criteriaQuery.from(ReaderEntity.class);
            readers = entityManager.createQuery(criteriaQuery).getResultList();
            logger.info("Get all readers");
        } catch(PersistenceException ex){
            throw new DaoException("Cannot get readers");
        } finally {
            entityManager.close();
        }
        return readers;
    }

    /**
     * @param r reader to insert
     * @throws DaoException exception
     */
    @Override
    public void save(ReaderEntity r) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(r);
            transaction.commit();
            logger.info("Save reader");
        } catch(PersistenceException ex){
            throw new DaoException("Cannot save reader");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    /**
     * @param id id of reader to delete
     * @throws DaoException exception
     */
    @Override
    public void delete(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaDelete<ReaderEntity> criteriaDelete = criteriaBuilder.createCriteriaDelete(ReaderEntity.class);
            Root<ReaderEntity> rootReader = criteriaDelete.from(ReaderEntity.class);
            Predicate condition = criteriaBuilder.equal(rootReader.get(ReaderEntity_.id), id);
            criteriaDelete.where(condition);

            transaction.begin();
            entityManager.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
            logger.info("Delete reader");
        } catch(PersistenceException ex){
            throw new DaoException("Cannot delete reader");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }
}
