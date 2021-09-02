package by.slizh.tutorsweb.service;

import by.slizh.tutorsweb.entity.User;
import by.slizh.tutorsweb.exception.ServiceException;

import java.util.Optional;

public interface UserService {

    Optional<User> authenticate(String email, String password) throws ServiceException;

}
