package com.example.lab5_ultimate.model.dao;

import com.example.lab5_ultimate.model.entity.UserEntity;
import com.example.lab5_ultimate.model.entity.UserEntity_;
import com.example.lab5_ultimate.model.exception.DaoException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserDao extends AbstractDao {
    public void signUp(String name, String password) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        UserEntity u = new UserEntity();
        u.setName(name);
        u.setPassword(password);
        u.setRole("user");
        try {
            transaction.begin();
            entityManager.persist(u);
            transaction.commit();
            logger.info("Save user");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot save user");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    public UserEntity verify(String name, String password) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserEntity u;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
            Root<UserEntity> rootReader = criteriaQuery.from(UserEntity.class);
            Predicate condition = criteriaBuilder.equal(rootReader.get(UserEntity_.name), name);
            criteriaQuery.where(condition);

            u = entityManager.createQuery(criteriaQuery).getSingleResult();
            logger.info("Get user by name");
            return u.getPassword().equals(password) ? u : null;
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot get reader with name = " + name);
        } finally {
            entityManager.close();
        }
    }
}
