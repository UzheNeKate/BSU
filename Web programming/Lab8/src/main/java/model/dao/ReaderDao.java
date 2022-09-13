package model.dao;

import model.entity.Reader;
import model.exception.DBConnectionException;
import model.exception.DaoException;
import model.exception.JDBCConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReaderDao extends AbstractDao implements Dao<Reader> {

    private static final String INSERT_READER_SQL = "insert into reader (name) values(?)";
    private static final String DELETE_READER_SQL = "delete from reader where id = ?";
    private static final String SELECT_ALL_READERS_SQL = "select * from reader";
    private static final String SELECT_READER_BY_ID_SQL = "select * from reader where id = ?";
    private static final String SELECT_READER_BY_NAME_SQL = "select * from reader where name = ?";

    /**
     * Constructor
     */
    public ReaderDao() throws DaoException {
        super();
    }

    /**
     * @param name name of reader
     * @return reader by the name
     * @throws DaoException
     */
    public Reader get(String name) throws DaoException {
        Reader reader = null;
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_READER_BY_NAME_SQL);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            reader = new Reader(id, name);
            logger.info("get reader by name");
        } catch (SQLException e) {
            throw new DaoException("Get reader by name exception ", e);
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
        return reader;
    }

    /**
     * @param id id of reader
     * @return reader by id
     * @throws DaoException
     */
    @Override
    public Reader get(int id) throws DaoException {
        Reader reader = null;
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_READER_BY_ID_SQL);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString(2);
                reader = new Reader(id, name);
            }
            logger.info("get reader by id");
        } catch (SQLException e) {
            throw new DaoException("Get reader exception ", e);
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
        return reader;
    }

    /**
     * @return list of all readers
     * @throws DaoException
     */
    @Override
    public List getAll() throws DaoException {
        var readers = new ArrayList<Reader>();
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_READERS_SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Reader reader = new Reader(id, name);
                readers.add(reader);
            }
            logger.info("read readers");
        } catch (SQLException e) {
            throw new DaoException("Get all readers exception ", e);
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
     * @param r reader to insert
     * @throws DaoException
     */
    @Override
    public void save(Reader r) throws DaoException {
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(INSERT_READER_SQL);
            stmt.setInt(1, r.getId());
            stmt.setString(2, r.getName());
            stmt.executeUpdate();
            logger.info("inserted reader");
        } catch (SQLException e) {
            throw new DaoException("Insert reader exception ", e);
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
     * @param r reader to delete
     * @throws DaoException
     */
    @Override
    public void delete(Reader r) throws DaoException {
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(DELETE_READER_SQL);
            stmt.setInt(1, r.getId());
            stmt.executeUpdate();
            logger.info("deleted reader");
        } catch (SQLException e) {
            throw new DaoException("Delete reader exception ", e);
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
}
