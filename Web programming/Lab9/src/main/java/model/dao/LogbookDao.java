package model.dao;

import model.entity.LogbookEntity;
import model.exception.DaoException;

import javax.persistence.*;
import java.util.List;

public class LogbookDao extends AbstractDao implements Dao<LogbookEntity> {
    private static final String SELECT_READERS_WITH_ARREARS_SQL =
            "select r.id, r.name from reader r left join logbook l " +
                    "on l.reader_id = r.id where timestampdiff(month, l.end_date, curdate()) > 1";

    /**
     * Constructor
     */
    public LogbookDao() {
        super();
    }

    /**
     * @return list of debtors
     * @throws DaoException
     */
    public List getDebtors() throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<LogbookEntity> logbook;
        try {
            transaction.begin();
            Query booksByName = entityManager.createNativeQuery(SELECT_READERS_WITH_ARREARS_SQL);
            logbook = booksByName.getResultList();
            transaction.commit();
            logger.info("Get debtors");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot get debtors");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
        return logbook;
    }

    /**
     * @param reader_id id of reader
     * @return logbook
     * @throws DaoException
     */
    @Override
    public LogbookEntity get(int reader_id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        LogbookEntity logbook;
        try {
            TypedQuery<LogbookEntity> booksByName = entityManager.createNamedQuery("logbookByReader", LogbookEntity.class);
            logbook = booksByName.getSingleResult();
            logger.info("Get record by reader id");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot ger reader by id " + reader_id);
        } finally {
            entityManager.close();
        }
        return logbook;
    }

    /**
     * @return list of all records from logbook
     * @throws DaoException
     */
    @Override
    public List<LogbookEntity> getAll() throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<LogbookEntity> logbooks;
        try {
            TypedQuery<LogbookEntity> booksByName = entityManager.createNamedQuery("allLogbooks", LogbookEntity.class);
            logbooks = booksByName.getResultList();
            logger.info("Get logbook");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot get logbook");
        } finally {
            entityManager.close();
        }
        return logbooks;
    }

    /**
     * @param l logbook
     * @throws DaoException
     */
    @Override
    public void save(LogbookEntity l) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(l);
            transaction.commit();
            logger.info("Save logbook record");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot add record to the logbook");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    /**
     * @param l logbook
     * @throws DaoException
     */
    @Override
    public void delete(LogbookEntity l) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(l) ? l : entityManager.merge(l));
            transaction.commit();
            logger.info("Delete logbook record");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot delete record from logbook");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }
}
