package model.dao;

import model.entity.LogbookEntity;
import model.entity.LogbookEntity_;
import model.entity.ReaderEntity;
import model.exception.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class LogbookDao extends AbstractDao implements Dao<LogbookEntity> {
    public static final int DAYS_TO_SUBTRACT = 30;

    /**
     * Constructor
     */
    public LogbookDao() {
        super();
    }

    /**
     * @return list of debtors
     * @throws DaoException exception
     */
    public List<ReaderEntity> getDebtors() throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LogbookEntity> cq = cb.createQuery(LogbookEntity.class);
        Root<LogbookEntity> root = cq.from(LogbookEntity.class);

        ParameterExpression<Date> parameter = cb.parameter(Date.class);
        Predicate cond = cb.lessThan(root.get(LogbookEntity_.endDate), parameter);
        cq.select(root).where(cond);
        List<ReaderEntity> debtors;
        try {
            transaction.begin();
            TypedQuery<LogbookEntity> tq = entityManager.createQuery(cq).setParameter(parameter, dateMonthAgo());

            List<LogbookEntity> logbooks = tq.getResultList();
            debtors = logbooks.stream().map(LogbookEntity::getReaderByReaderId).collect(Collectors.toList());
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
        return debtors;
    }

    private Date dateMonthAgo() {
        return Date.valueOf(LocalDate.now().minusDays(DAYS_TO_SUBTRACT));
    }

    /**
     * @param id id of logbook
     * @return logbook
     * @throws DaoException exception
     */
    @Override
    public LogbookEntity get(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        LogbookEntity logbook;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<LogbookEntity> criteriaQuery = criteriaBuilder.createQuery(LogbookEntity.class);
            Root<LogbookEntity> rootLogbook = criteriaQuery.from(LogbookEntity.class);
            Predicate condition = criteriaBuilder.equal(rootLogbook.get(LogbookEntity_.id), id);
            criteriaQuery.where(condition);

            logbook = entityManager.createQuery(criteriaQuery).getSingleResult();
            logger.info("Get records by reader id");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot get logbook by id " + id);
        } finally {
            entityManager.close();
        }
        return logbook;
    }

    /**
     * @param reader the reader
     * @return logbook
     * @throws DaoException exception
     */
    public List<LogbookEntity> get(ReaderEntity reader) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<LogbookEntity> logbooks;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<LogbookEntity> criteriaQuery = criteriaBuilder.createQuery(LogbookEntity.class);
            Root<LogbookEntity> rootLogbook = criteriaQuery.from(LogbookEntity.class);
            Predicate condition = criteriaBuilder.equal(rootLogbook.get(LogbookEntity_.readerByReaderId), reader);
            criteriaQuery.where(condition);

            logbooks = entityManager.createQuery(criteriaQuery).getResultList();
            logger.info("Get records by reader id");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot get logbooks by id for reader id " + reader.getId());
        } finally {
            entityManager.close();
        }
        return logbooks;
    }

    /**
     * @return list of all records from logbook
     * @throws DaoException exception
     */
    @Override
    public List<LogbookEntity> getAll() throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<LogbookEntity> logbooks;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<LogbookEntity> criteriaQuery = criteriaBuilder.createQuery(LogbookEntity.class);
            Root<LogbookEntity> book = criteriaQuery.from(LogbookEntity.class);
            logbooks = entityManager.createQuery(criteriaQuery).getResultList();
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
     * @throws DaoException exception
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
     * @param id id of logbook
     * @throws DaoException exception
     */
    @Override
    public void delete(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaDelete<LogbookEntity> criteriaDelete = criteriaBuilder.createCriteriaDelete(LogbookEntity.class);
            Root<LogbookEntity> rootReader = criteriaDelete.from(LogbookEntity.class);
            Predicate condition = criteriaBuilder.equal(rootReader.get(LogbookEntity_.id), id);
            criteriaDelete.where(condition);

            transaction.begin();
            entityManager.createQuery(criteriaDelete).executeUpdate();
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
