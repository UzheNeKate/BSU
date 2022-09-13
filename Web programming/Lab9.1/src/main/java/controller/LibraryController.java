package controller;

import model.dao.BookDao;
import model.dao.LogbookDao;
import model.dao.ReaderDao;
import model.entity.BookEntity;
import model.entity.LogbookEntity;
import model.entity.ReaderEntity;
import model.exception.ControllerException;
import model.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Date;
import java.util.List;

public class LibraryController {

    private final Logger logger = LogManager.getLogger();

    private final ReaderDao readerDao;
    private final BookDao bookDao;
    private final LogbookDao logbookDao;

    /**
     * Constructor of controller
     */
    public LibraryController() {
        readerDao = new ReaderDao();
        bookDao = new BookDao();
        logbookDao = new LogbookDao();
    }

    /**
     * @param title  title of the book
     * @param author author of the book
     * @return number of items
     */
    public int GetNumberOfBook(String title, String author) throws ControllerException {
        try {
            logger.info("Controller: getting book by name");
            return bookDao.getBookByName(title, author).getNumOfItems();
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
}
