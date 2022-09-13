package model.dao;

import model.exception.DaoException;

import java.util.List;

public interface Dao<T> {

        T get(int id) throws DaoException;

        List<T> getAll() throws DaoException;

        void save(T t) throws DaoException;

        void delete(T t) throws DaoException;
}