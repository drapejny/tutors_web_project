package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.entity.Entity;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.connection.ProxyConnection;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<K, T extends Entity> {

    protected ProxyConnection connection;

    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<T> findById(K id) throws DaoException;

    public abstract boolean deleteById(K id) throws DaoException;

    public abstract boolean create(T t) throws DaoException;

    public abstract T update(T t) throws DaoException;

    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }
}
