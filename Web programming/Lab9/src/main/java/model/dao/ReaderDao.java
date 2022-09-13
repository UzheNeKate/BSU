package model.dao;

import model.entity.ReaderEntity;
import model.exception.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
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
     * @throws DaoException
     */
    public ReaderEntity get(String name) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ReaderEntity r;
        try {
            TypedQuery<ReaderEntity> readerByName =
                    entityManager.createNamedQuery("readerByName", ReaderEntity.class);
            readerByName.setParameter(1, name);
            r = readerByName.getSingleResult();
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
     * @throws DaoException
     */
    @Override
    public ReaderEntity get(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ReaderEntity r;
        try {
            r = entityManager.find(ReaderEntity.class, id);
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
     * @throws DaoException
     */
    @Override
    public List<ReaderEntity> getAll() throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<ReaderEntity> readers;
        try {
            TypedQuery<ReaderEntity> booksByName =
                    entityManager.createNamedQuery("allReaders", ReaderEntity.class);
            readers = booksByName.getResultList();
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
     * @throws DaoException
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
     * @param r reader to delete
     * @throws DaoException
     */
    @Override
    public void delete(ReaderEntity r) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(r) ? r : entityManager.merge(r));
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
