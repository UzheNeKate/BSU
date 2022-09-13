package model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDao {

    protected Logger logger = LogManager.getLogger();
    protected EntityManagerFactory entityManagerFactory;

    /**
     * Constructor
     */
    public AbstractDao() {
        logger.info("Creating factory...");
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    /**
     * Close connection
     */
    public void closeConnection(){
        entityManagerFactory.close();
        logger.info("Connection closed");
    }
}
