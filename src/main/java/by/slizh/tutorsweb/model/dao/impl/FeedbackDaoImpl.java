package by.slizh.tutorsweb.model.dao.impl;

import by.slizh.tutorsweb.entity.Feedback;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.dao.FeedbackDao;

import java.util.List;
import java.util.Optional;

public class FeedbackDaoImpl extends FeedbackDao {

    @Override
    public List<Feedback> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Feedback> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Feedback feedback) throws DaoException {
        return false;
    }

    @Override
    public Feedback update(Feedback feedback) throws DaoException {
        return null;
    }
}
