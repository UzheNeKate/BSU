package model.dao;

import model.entity.BookEntity;
import model.exception.DaoException;

import javax.persistence.*;
import java.util.List;

public class BookDao extends AbstractDao implements Dao<BookEntity> {
    public BookDao() {
        super();
    }

    /**
     * @param title  title of book
     * @param author author of book
     * @return book with name
     * @throws DaoException
     */
    public BookEntity getBookByName(String title, String author) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        BookEntity b;
        try {
            TypedQuery<BookEntity> booksByName = entityManager.createNamedQuery("bookByName", BookEntity.class);
            booksByName.setParameter(1, title);
            booksByName.setParameter(2, author);
            b = booksByName.getSingleResult();
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
     * @throws DaoException
     */
    public void removeItemBook(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Query q = entityManager.createNamedQuery("removeItem");
            q.setParameter(1, id);
            q.executeUpdate();
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
     * @throws DaoException
     */
    public void addItemBook(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Query q = entityManager.createNamedQuery("addItem");
            q.setParameter(1, id);
            q.executeUpdate();
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
     * @throws DaoException
     */
    public List<BookEntity> getBooks(String author) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<BookEntity> books;
        try {
            TypedQuery<BookEntity> booksByName = entityManager.createNamedQuery("booksByAuthor", BookEntity.class);
            booksByName.setParameter(1, author);
            books = booksByName.getResultList();
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
     * @throws DaoException
     */
    @Override
    public BookEntity get(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        BookEntity b;
        try {
            b = entityManager.find(BookEntity.class, id);
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
     * @throws DaoException
     */
    @Override
    public List<BookEntity> getAll() throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<BookEntity> books;
        try {
            TypedQuery<BookEntity> booksByName = entityManager.createNamedQuery("allBooks", BookEntity.class);
            books = booksByName.getResultList();
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
     * @throws DaoException
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
     * @param b book to delete
     * @throws DaoException
     */
    @Override
    public void delete(BookEntity b) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(b) ? b : entityManager.merge(b));
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
