package model.dao;

import model.ConnectionPool;
import model.exception.DBConnectionException;
import model.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractDao {

    protected Logger logger = LogManager.getLogger();
    private ConnectionPool dbc;

    /**
     * Constructor
     */
    public AbstractDao() throws DaoException {
        try {
            dbc = ConnectionPool.getInstance();
            logger.info("Connection to database from dao inited");
        } catch (DBConnectionException e) {
            throw new DaoException("Cannot create DBConnectorPool ", e);
        }
    }

    protected ConnectionPool getDBConnector() {
        logger.info("requested to db connector");
        return dbc;
    }
}
