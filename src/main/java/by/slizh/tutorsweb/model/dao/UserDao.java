package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.entity.User;
import by.slizh.tutorsweb.exception.DaoException;

import java.util.Optional;

public abstract class UserDao extends AbstractDao<Integer, User> {

    public abstract boolean create(User user, String password) throws DaoException;

    public abstract boolean updateUserPassword(User user, String password) throws DaoException;

    public abstract Optional<User> findUserByEmail(String email) throws DaoException;

    public abstract String findUserPassword(User user) throws DaoException;

}
