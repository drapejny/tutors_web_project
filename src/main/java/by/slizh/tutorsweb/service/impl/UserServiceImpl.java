package by.slizh.tutorsweb.service.impl;

import by.slizh.tutorsweb.entity.User;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.dao.EntityTransaction;
import by.slizh.tutorsweb.model.dao.UserDao;
import by.slizh.tutorsweb.model.dao.impl.UserDaoImpl;
import by.slizh.tutorsweb.service.UserService;
import by.slizh.tutorsweb.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();

    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> authenticate(String email, String password) throws ServiceException {
        Optional<User> result = Optional.empty();
        String passwordHash;
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.init(userDao);
            Optional<User> optionalUser = userDao.findUserByEmail(email);
            if (optionalUser.isPresent()) {
                passwordHash = userDao.findUserPassword(optionalUser.get());
                String hashedPassword = PasswordEncoder.encodePasswrord(password);
                if (passwordHash.equals(hashedPassword)) {
                    result = optionalUser;
                }
            }
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Can't rollback transaction in authenticate method", e);
            }
            throw new ServiceException("Failed to make transaction in authenticate method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                throw new ServiceException("Can't end transaction in authenticate method", e);
            }
        }
        return result;
    }
}
