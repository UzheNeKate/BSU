package model.dao;

import model.entity.Book;
import model.exception.DBConnectionException;
import model.exception.DaoException;
import model.exception.JDBCConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends AbstractDao implements Dao<Book>{

    private static final String INSERT_BOOK_SQL = "insert into book (title, author, num_of_items) values(?, ?, ?)";
    private static final String DELETE_BOOK_SQL = "delete from book where id = ?";
    private static final String SELECT_ALL_BOOKS_SQL = "select * from book";
    private static final String SELECT_BOOK_BY_ID_SQL = "select * from book where id = ?";
    private static final String SELECT_BOOK_BY_NAME_SQL = "select * from book where title = ? and author = ?";
    private static final String DELETE_ITEM_BOOK_SQL = "update book set num_of_items = num_of_items - 1 where id = ?";
    private static final String ADD_ITEM_BOOK_SQL = "update book set num_of_items = num_of_items + 1 where id = ?";
    private static final String SELECT_BOOKS_BY_AUTHOR_SQL = "select * from book where author = ?";

    public BookDao() throws DaoException {
        super();
    }

    /**
     * @param title title of book
     * @param author author of book
     * @return book with name
     * @throws DaoException
     */
    public Book getBookByName(String title, String author) throws DaoException {
        Book book = null;
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_BOOK_BY_NAME_SQL);
            stmt.setString(1, title);
            stmt.setString(2, author);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int num = rs.getInt(4);
                book = new Book(id, title, author, num);
            }
            logger.info("get book by name");
        } catch (SQLException e) {
            throw new DaoException("Delete book exception ", e);
        } catch (DBConnectionException e) {
            throw new DaoException("Faild to get connection from db connector ", e);
        } finally {
            if (connection != null) {
                try {
                    getDBConnector().releaseConnection(connection);
                } catch (DBConnectionException e) {
                    throw new DaoException("Failed to return connection to db connector ", e);
                }
            }
        }
        return book;
    }

    /**
     * @param id id of book to remove one item
     * @throws DaoException
     */
    public void removeItemBook(int id) throws DaoException{
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(DELETE_ITEM_BOOK_SQL);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("remove book item by id");
        } catch (SQLException e) {
            throw new DaoException("Remove item book exception ", e);
        } catch (DBConnectionException e) {
            throw new DaoException("Faild to get connection from db connector ", e);
        } finally {
            if (connection != null) {
                try {
                    getDBConnector().releaseConnection(connection);
                } catch (DBConnectionException e) {
                    throw new DaoException("Failed to return connection to db connector ", e);
                }
            }
        }
    }

    /**
     * @param id id of book to add one item
     * @throws DaoException
     */
    public void addItemBook(int id) throws DaoException{
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(ADD_ITEM_BOOK_SQL);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("add book item by id");
        } catch (SQLException e) {
            throw new DaoException("Add item book exception ", e);
        } catch (DBConnectionException e) {
            throw new DaoException("Faild to get connection from db connector ", e);
        } finally {
            if (connection != null) {
                try {
                    getDBConnector().releaseConnection(connection);
                } catch (DBConnectionException e) {
                    throw new DaoException("Failed to return connection to db connector ", e);
                }
            }
        }
    }

    /**
     * @param author author to get books
     * @return books of author
     * @throws DaoException
     */
    public List getBooks(String author) throws DaoException {
        var books = new ArrayList<Book>();
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_BOOKS_BY_AUTHOR_SQL);
            stmt.setString(1, author);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                int num = rs.getInt(4);
                books.add(new Book(id, title, author, num));
            }
            logger.info("read books");
        } catch (SQLException e) {
            throw new DaoException("Get all books exception ", e);
        } catch (DBConnectionException e) {
            throw new DaoException("Failed to get connection from connector", e);
        } finally {
            if (connection != null) {
                try {
                    getDBConnector().releaseConnection(connection);
                } catch (DBConnectionException e) {
                    throw new DaoException("Failed to return connection to db connector ", e);
                }
            }
        }
        return books;
    }

    /**
     * @param id id of book to get
     * @return book with id
     * @throws DaoException
     */
    @Override
    public Book get(int id) throws DaoException {
        Book book = null;
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_BOOK_BY_ID_SQL);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString(2);
                String author = rs.getString(3);
                int num = rs.getInt(4);
                book = new Book(id, title, author, num);
            }
            logger.info("get book by id");
        } catch (SQLException e) {
            throw new DaoException("Delete book exception ", e);
        } catch (DBConnectionException e) {
            throw new DaoException("Faild to get connection from db connector ", e);
        } finally {
            if (connection != null) {
                try {
                    getDBConnector().releaseConnection(connection);
                } catch (DBConnectionException e) {
                    throw new DaoException("Failed to return connection to db connector ", e);
                }
            }
        }
        return book;
    }

    /**
     * @return all books
     * @throws DaoException
     */
    @Override
    public List getAll() throws DaoException {
        var books = new ArrayList<Book>();
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_BOOKS_SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                int num = rs.getInt(4);
                books.add(new Book(id, title, author, num));
            }
            logger.info("read books");
        } catch (SQLException e) {
            throw new DaoException("Get all books exception ", e);
        } catch (DBConnectionException e) {
            throw new DaoException("Failed to get connection from connector", e);
        } finally {
            if (connection != null) {
                try {
                   getDBConnector().releaseConnection(connection);
                } catch (DBConnectionException e) {
                    throw new DaoException("Failed to return connection to db connector ", e);
                }
            }
        }
        return books;
    }

    /**
     * @param b book to add
     * @throws DaoException
     */
    @Override
    public void save(Book b) throws DaoException {
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(INSERT_BOOK_SQL);
            stmt.setString(1, b.getTitle());
            stmt.setString(2, b.getAuthor());
            stmt.setInt(3, b.getNumOfItems());
            stmt.executeUpdate();
            logger.info("inserted book");
        } catch (SQLException e) {
            throw new DaoException("Insert book exception ", e);
        } catch (DBConnectionException e) {
            throw new DaoException("Faild to get connection from db connector ", e);
        } finally {
            if (connection != null) {
                try {
                    getDBConnector().releaseConnection(connection);
                } catch (DBConnectionException e) {
                    throw new DaoException("Failed to return connection to db connector ", e);
                }
            }
        }
    }

    /**
     * @param b book to delete
     * @throws DaoException
     */
    @Override
    public void delete(Book b) throws DaoException {
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(DELETE_BOOK_SQL);
            stmt.setInt(1, b.getId());
            stmt.executeUpdate();
            logger.info("deleted book");
        } catch (SQLException e) {
            throw new DaoException("Delete book exception ", e);
        }  catch (DBConnectionException e) {
            throw new DaoException("Faild to get connection from db connector ", e);
        } finally {
            if (connection != null) {
                try {
                    getDBConnector().releaseConnection(connection);
                } catch (DBConnectionException e) {
                    throw new DaoException("Failed to return connection to db connector ", e);
                }
            }
        }
    }
}
