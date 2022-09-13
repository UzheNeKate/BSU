package model.dao;

import model.entity.Logbook;
import model.entity.Reader;
import model.exception.DBConnectionException;
import model.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogbookDao extends AbstractDao implements Dao<Logbook>{

    private static final String INSERT_RECORD_SQL = "insert into logbook (book_id, reader_id, end_date) values(?, ?, ?)";
    private static final String DELETE_RECORD_SQL = "delete from logbook where book_id = ? and reader_id = ?";
    private static final String SELECT_ALL_RECORDS_SQL = "select * from logbook";
    private static final String SELECT_BOOKS_BY_READER_SQL = "select * from logbook where reader_id = ?";
    private static final String SELECT_READERS_WITH_ARREARS_SQL = "select r.id, r.name from reader r left join logbook l " +
            "on l.reader_id = r.id where timestampdiff(month, l.end_date, curdate()) > 1";

    /**
     * Constructor
     */
    public LogbookDao() throws DaoException {
        super();
    }

    /**
     * @return list of debtors
     * @throws DaoException
     */
    public List getDebtors() throws DaoException {
        var readers = new ArrayList<Reader>();
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_READERS_WITH_ARREARS_SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                readers.add(new Reader(id, name));
            }
            logger.info("get debtors");
        } catch (SQLException e) {
            throw new DaoException("Get all debtors exception ", e);
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
        return readers;
    }

    /**
     * @param reader_id id of reader
     * @return logbook
     * @throws DaoException
     */
    @Override
    public Logbook get(int reader_id) throws DaoException {
        Logbook l = null;
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_BOOKS_BY_READER_SQL);
            stmt.setInt(1, reader_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int book_id = rs.getInt(2);
                Date end_date = rs.getDate(3);
                l = new Logbook(book_id, reader_id, end_date);
            }
            logger.info("get logbook record by id");
        } catch (SQLException e) {
            throw new DaoException("Get logbook record exception ", e);
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
        return l;
    }

    /**
     * @return list of all records from logbook
     * @throws DaoException
     */
    @Override
    public List getAll() throws DaoException {
        var logs = new ArrayList<Logbook>();
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_RECORDS_SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int bookId = rs.getInt(2);
                int readerId = rs.getInt(1);
                Date endDate = rs.getDate(3);
                logs.add(new Logbook(bookId, readerId, endDate));
            }
            logger.info("read logbook records");
        } catch (SQLException e) {
            throw new DaoException("Get all logbook records exception ", e);
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
        return logs;
    }

    /**
     * @param l logbook
     * @throws DaoException
     */
    @Override
    public void save(Logbook l) throws DaoException {
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(INSERT_RECORD_SQL);
            stmt.setInt(2, l.getReaderId());
            stmt.setInt(1, l.getBookId());
            stmt.setDate(3, l.getEndDate());
            stmt.executeUpdate();
            logger.info("inserted logbook record");
        } catch (SQLException e) {
            throw new DaoException("Insert logbook record exception ", e);
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
     * @param l logbook
     * @throws DaoException
     */
    @Override
    public void delete(Logbook l) throws DaoException {
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(DELETE_RECORD_SQL);
            stmt.setInt(1, l.getBookId());
            stmt.setInt(2, l.getReaderId());
            stmt.executeUpdate();
            logger.info("delete logbook record");
        } catch (SQLException e) {
            throw new DaoException("Delete logbook exception ", e);
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
