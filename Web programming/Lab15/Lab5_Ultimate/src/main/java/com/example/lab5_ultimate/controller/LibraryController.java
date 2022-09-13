package com.example.lab5_ultimate.controller;

import com.example.lab5_ultimate.model.dao.BookDao;
import com.example.lab5_ultimate.model.dao.LogbookDao;
import com.example.lab5_ultimate.model.dao.ReaderDao;
import com.example.lab5_ultimate.model.dao.UserDao;
import com.example.lab5_ultimate.model.entity.BookEntity;
import com.example.lab5_ultimate.model.entity.LogbookEntity;
import com.example.lab5_ultimate.model.entity.ReaderEntity;
import com.example.lab5_ultimate.model.entity.UserEntity;
import com.example.lab5_ultimate.model.exception.ControllerException;
import com.example.lab5_ultimate.model.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;

public class LibraryController {

    private final Logger logger = LogManager.getLogger();

    private final ReaderDao readerDao;
    private final BookDao bookDao;
    private final LogbookDao logbookDao;
    private final UserDao userDao;

    /**
     * Constructor of controller
     */
    public LibraryController() {
        userDao = new UserDao();
        readerDao = new ReaderDao();
        bookDao = new BookDao();
        logbookDao = new LogbookDao();
    }

    /**
     * @param title  title of the book
     * @param author author of the book
     * @return number of items
     */
    public BookEntity GetNumberOfBook(String title, String author) throws ControllerException {
        try {
            logger.info("Controller: getting book by name");
            return bookDao.getBookByName(title, author);
        } catch (DaoException e) {
            logger.error("Something wrong!" + e.getMessage());
            throw new ControllerException("Cannot get number of items for book " + title);
        }
    }

    /**
     * @return list of readers that have debt
     */
    public List<ReaderEntity> GetDebtors() throws ControllerException {
        try {
            logger.info("Controller: getting debtors by name");
            return logbookDao.getDebtors();
        } catch (DaoException e) {
            logger.error("Something wrong!" + e.getMessage());
            throw new ControllerException("Cannot get debtors!");
        }
    }

    /**
     * @param author author of books
     * @return list of books written by this author
     */
    public List GetBooks(String author) throws ControllerException {
        try {
            logger.info("Controller: getting books by author");
            return bookDao.getBooks(author);
        } catch (DaoException e) {
            logger.error("Something wrong!" + e.getMessage());
            throw new ControllerException("Cannot get books of " + author + " author!");
        }
    }

    /**
     * @param title  title of book
     * @param author author of book
     * @param reader person who takes the book
     * @param date   date where this book need to be returned
     */
    public void GiveBook(String title, String author, String reader, Date date) throws ControllerException {
        BookEntity book;
        try {
            book = bookDao.getBookByName(title, author);
            ReaderEntity r = readerDao.get(reader);
            LogbookEntity logbookEntity = new LogbookEntity();
            logbookEntity.setReaderByReaderId(r);
            logbookEntity.setBookByBookId(book);
            logbookEntity.setEndDate(date);
            logbookDao.save(logbookEntity);
            bookDao.removeItemBook(book.getId());
            logger.info("Controller: giving book for reader");
        } catch (DaoException e) {
            logger.warn("Something wrong!" + e.getMessage());
            throw new ControllerException("Cannot give book");
        }
    }

    public void deleteBook(int id) throws ControllerException {
        try {
            bookDao.removeItemBook(id);
        } catch (DaoException e) {
            throw new ControllerException("Cannot remove book");
        }
    }

    public void Register(String name, String password) throws ControllerException {
        try {
            userDao.signUp(name, password);
            ReaderEntity r = new ReaderEntity();
            r.setName(name);
            readerDao.save(r);
        } catch(DaoException e){
            logger.warn("Something wrong!" + e.getMessage());
            throw new ControllerException("Cannot register user");
        }
    }

    public UserEntity verify(String name, String password) throws ControllerException {
        try {
            return userDao.verify(name, password);
        } catch(DaoException e){
            logger.warn("Something wrong!" + e.getMessage());
            throw new ControllerException("Cannot find user");
        }
    }
}
