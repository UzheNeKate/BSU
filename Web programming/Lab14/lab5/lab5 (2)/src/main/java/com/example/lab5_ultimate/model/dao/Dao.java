package com.example.lab5_ultimate.model.dao;

import com.example.lab5_ultimate.model.exception.DaoException;
import java.util.List;

public interface Dao<T> {

        T get(int id) throws DaoException;

        List<T> getAll() throws DaoException;

        void save(T t) throws DaoException;

        void delete(int id) throws DaoException;
}