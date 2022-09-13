package com.example.lab5_ultimate.model.dao;

import com.example.lab5_ultimate.model.entity.BookEntity;
import com.example.lab5_ultimate.model.entity.BookEntity_;
import com.example.lab5_ultimate.model.exception.DaoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.*;

import java.util.List;

public class BookDao extends AbstractDao implements Dao<BookEntity> {
    public BookDao() {
        super();
    }

    /**
     * @param title  title of book
     * @param author author of book
     * @return book with name
     * @throws DaoException exception
     */
    public BookEntity getBookByName(String title, String author) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        BookEntity b;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<BookEntity> criteriaQuery = criteriaBuilder.createQuery(BookEntity.class);
            Root<BookEntity> rootClient = criteriaQuery.from(BookEntity.class);
            Predicate condition = criteriaBuilder.equal(rootClient.get(BookEntity_.title), title);
            Predicate condition1 = criteriaBuilder.equal(rootClient.get(BookEntity_.author), author);
            criteriaQuery.where(condition, condition1);

            b = entityManager.createQuery(criteriaQuery).getSingleResult();
            logger.info("Get book by name");
        }  catch (PersistenceException ex) {
            throw new DaoException("Cannot get book with title " + title);
        } finally {
            entityManager.close();
        }
        return b;
    }

    /**
     * @param id id of book to remove one item
     * @throws DaoException exception
     */
    public void removeItemBook(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            BookEntity book = get(id);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<BookEntity> update = criteriaBuilder.createCriteriaUpdate(BookEntity.class);
            Root<BookEntity> rootBook = update.from(BookEntity.class);
            update.set(rootBook.get(BookEntity_.numOfItems), book.getNumOfItems() - 1);
            Predicate condition = criteriaBuilder.equal(rootBook.get(BookEntity_.id), id);
            update.where(condition);

            transaction.begin();
            entityManager.createQuery(update).executeUpdate();
            transaction.commit();
            logger.info("Remove book");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot remove item of book with id = " + id);
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    /**
     * @param id id of book to add one item
     * @throws DaoException exception
     */
    public void addItemBook(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            BookEntity book = get(id);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<BookEntity> update = criteriaBuilder.createCriteriaUpdate(BookEntity.class);
            Root<BookEntity> rootBook = update.from(BookEntity.class);
            update.set(rootBook.get(BookEntity_.numOfItems), book.getNumOfItems() + 1);
            Predicate condition = criteriaBuilder.equal(rootBook.get(BookEntity_.id), id);
            update.where(condition);

            transaction.begin();
            entityManager.createQuery(update).executeUpdate();
            transaction.commit();
            logger.info("Add item of book");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot add item of book with id = " + id);
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    /**
     * @param author author to get books
     * @return books of author
     * @throws DaoException exception
     */
    public List<BookEntity> getBooks(String author) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<BookEntity> books;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<BookEntity> criteriaQuery = criteriaBuilder.createQuery(BookEntity.class);
            Root<BookEntity> rootBook = criteriaQuery.from(BookEntity.class);
            Predicate condition = criteriaBuilder.equal(rootBook.get(BookEntity_.author), author);
            criteriaQuery.where(condition);

            books = entityManager.createQuery(criteriaQuery).getResultList();
            logger.info("Get books by author");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot get books of " + author);
        } finally {
            entityManager.close();
        }
        return books;
    }

    /**
     * @param id id of book to get
     * @return book with id
     * @throws DaoException exception
     */
    @Override
    public BookEntity get(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        BookEntity b;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<BookEntity> criteriaQuery = criteriaBuilder.createQuery(BookEntity.class);
            Root<BookEntity> rootBook = criteriaQuery.from(BookEntity.class);
            Predicate condition = criteriaBuilder.equal(rootBook.get(BookEntity_.id), id);
            criteriaQuery.where(condition);

            b = entityManager.createQuery(criteriaQuery).getSingleResult();
            logger.info("Get book by id");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot get book with id = " + id);
        } finally {
            entityManager.close();
        }
        return b;
    }

    /**
     * @return all books
     * @throws DaoException exception
     */
    @Override
    public List<BookEntity> getAll() throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<BookEntity> books;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<BookEntity> criteriaQuery = criteriaBuilder.createQuery(BookEntity.class);
            Root<BookEntity> book = criteriaQuery.from(BookEntity.class);
            books = entityManager.createQuery(criteriaQuery).getResultList();
            logger.info("Get all books");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot get books");
        } finally {
            entityManager.close();
        }
        return books;
    }

    /**
     * @param b book to add
     * @throws DaoException exception
     */
    @Override
    public void save(BookEntity b) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(b);
            transaction.commit();
            logger.info("Save book");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot save book");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    /**
     * @param id od of book to delete
     * @throws DaoException exception
     */
    @Override
    public void delete(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaDelete<BookEntity> criteriaDelete = criteriaBuilder.createCriteriaDelete(BookEntity.class);
            Root<BookEntity> rootBook = criteriaDelete.from(BookEntity.class);
            Predicate condition = criteriaBuilder.equal(rootBook.get(BookEntity_.id), id);
            criteriaDelete.where(condition);

            transaction.begin();
            entityManager.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
            logger.info("Delete book");
        } catch (PersistenceException ex) {
            throw new DaoException("Cannot delete book");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }
}
