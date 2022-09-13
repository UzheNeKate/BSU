package com.example.lab5_ultimate.model.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
