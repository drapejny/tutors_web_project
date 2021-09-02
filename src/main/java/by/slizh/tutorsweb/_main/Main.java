package by.slizh.tutorsweb._main;

import by.slizh.tutorsweb.entity.Subject;
import by.slizh.tutorsweb.entity.Tutor;
import by.slizh.tutorsweb.entity.User;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.dao.EntityTransaction;
import by.slizh.tutorsweb.model.dao.SubjectDao;
import by.slizh.tutorsweb.model.dao.TutorDao;
import by.slizh.tutorsweb.model.dao.UserDao;
import by.slizh.tutorsweb.model.dao.impl.SubjectDaoImpl;
import by.slizh.tutorsweb.model.dao.impl.TutorDaoImpl;
import by.slizh.tutorsweb.model.dao.impl.UserDaoImpl;
import by.slizh.tutorsweb.service.UserService;
import by.slizh.tutorsweb.service.impl.UserServiceImpl;
import by.slizh.tutorsweb.util.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, DaoException, ServiceException {
        Tutor tutor = new Tutor.TutorBuilder()
                .setUserId(5)
                .setTutorId(1)
                .setFirstName("Anton")
                .setLastName("Slizh")
                .setEmail("slizh@yandex.by")
                .setCity("Grodno")
                .setRole(User.Role.TUTOR)
                .setStatus(User.Status.ACTIVE)
                .setPhone("7788")
                .setEducation("BSUIR")
                .setInfo("info bla bla bla")
                .setPricePerHour(BigDecimal.valueOf(25))
                .createTutor();
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userDao);
        Optional<User> user = userDao.findUserByEmail("slizh@yandex.by");
        if (user.isPresent()){
            System.out.println(user.get().getLastName());
        }
        String pass = userDao.findUserPassword(user.get());
        System.out.println(pass);
        System.out.println(PasswordEncoder.encodePasswrord("123"));
        transaction.end();

        UserService service = UserServiceImpl.getInstance();
        Optional<User> user1 = service.authenticate("slizh@yandex.by","123");
        System.out.println(user1.get().getLastName());
    }
}
