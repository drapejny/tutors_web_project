package by.slizh.tutorsweb.model.dao.impl;

import by.slizh.tutorsweb.entity.Tutor;
import by.slizh.tutorsweb.entity.User;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.dao.ColumnName;
import by.slizh.tutorsweb.model.dao.TutorDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class TutorDaoImpl extends TutorDao {

    private static final String SQL_FIND_ALL_TUTORS = """
             SELECT tutor_id, phone, education, info, price_per_hour, rejection_message,
                                users.user_id, first_name, last_name, email, city, photo, role_name, status_name
             FROM tutors
             JOIN users ON tutors.user_id = users.user_id
             JOIN role ON users.role_id = role.role_id
             JOIN status ON users.status_id = status.status_id;
            """;
    private static final String SQL_FIND_TUTOR_BY_ID = """
            SELECT tutor_id, phone, education, info, price_per_hour, rejection_message,
                                users.user_id, first_name, last_name, email, city, photo, role_name, status_name
             FROM tutors
             JOIN users ON tutors.user_id = users.user_id
             JOIN role ON users.role_id = role.role_id
             JOIN status ON users.status_id = status.status_id
             WHERE tutor_id = ?;
            """;
    private static final String SQL_DELETE_TUTOR_BY_ID = """
            DELETE FROM tutors WHERE tutor_id = ?;
            """;
    private static final String SQL_CREATE_TUTOR = """
            INSERT INTO tutors(user_id, phone, education, info, price_per_hour)
            VALUES (?, ?, ?, ?, ?);
            """;
    private static final String SQL_UPDATE_TUTOR = """
            UPDATE tutors
            SET phone = ?, education = ?, info = ?, price_per_hour = ?, rejection_message = ?
            WHERE tutor_id = ?;
            """;

    @Override
    public List<Tutor> findAll() throws DaoException {
        List<Tutor> tutors = new LinkedList<Tutor>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_TUTORS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tutor tutor = new Tutor.TutorBuilder()
                        .setTutorId(resultSet.getInt(ColumnName.TUTOR_ID))
                        .setPhone(resultSet.getString(ColumnName.PHONE))
                        .setEducation(resultSet.getString(ColumnName.EDUCATION))
                        .setInfo(resultSet.getString(ColumnName.INFO))
                        .setPricePerHour(resultSet.getBigDecimal(ColumnName.PRICE_PER_HOUR))
                        .setRejectionMessage(resultSet.getString(ColumnName.PRICE_PER_HOUR))
                        .setUserId(resultSet.getInt(ColumnName.USER_ID))
                        .setFirstName(resultSet.getString(ColumnName.FIRST_NAME))
                        .setLastName(resultSet.getString(ColumnName.LAST_NAME))
                        .setEmail(resultSet.getString(ColumnName.EMAIL))
                        .setCity(resultSet.getString(ColumnName.CITY))
                        .setPhoto(resultSet.getBinaryStream(ColumnName.PHOTO))
                        .setRole(User.Role.valueOf(resultSet.getString(ColumnName.ROLE_NAME).toUpperCase(Locale.ROOT)))
                        .setStatus(User.Status.valueOf(resultSet.getString(ColumnName.STATUS_NAME).toUpperCase(Locale.ROOT)))
                        .createTutor();
                tutors.add(tutor);
            }
            return tutors;
        } catch (SQLException e) {
            throw new DaoException("Failed to find all tutors", e);
        }
    }

    @Override
    public Optional<Tutor> findById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TUTOR_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Tutor tutor = new Tutor.TutorBuilder()
                        .setTutorId(resultSet.getInt(ColumnName.TUTOR_ID))
                        .setPhone(resultSet.getString(ColumnName.PHONE))
                        .setEducation(resultSet.getString(ColumnName.EDUCATION))
                        .setInfo(resultSet.getString(ColumnName.INFO))
                        .setPricePerHour(resultSet.getBigDecimal(ColumnName.PRICE_PER_HOUR))
                        .setRejectionMessage(resultSet.getString(ColumnName.PRICE_PER_HOUR))
                        .setUserId(resultSet.getInt(ColumnName.USER_ID))
                        .setFirstName(resultSet.getString(ColumnName.FIRST_NAME))
                        .setLastName(resultSet.getString(ColumnName.LAST_NAME))
                        .setEmail(resultSet.getString(ColumnName.EMAIL))
                        .setCity(resultSet.getString(ColumnName.CITY))
                        .setPhoto(resultSet.getBinaryStream(ColumnName.PHOTO))
                        .setRole(User.Role.valueOf(resultSet.getString(ColumnName.ROLE_NAME).toUpperCase(Locale.ROOT)))
                        .setStatus(User.Status.valueOf(resultSet.getString(ColumnName.STATUS_NAME).toUpperCase(Locale.ROOT)))
                        .createTutor();
                return Optional.of(tutor);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find tutor by id", e);
        }
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TUTOR_BY_ID)) {
            statement.setInt(1, id);
            boolean result = statement.executeUpdate() == 1;
            return result;
        } catch (SQLException e) {
            throw new DaoException("Failed to delete tutor by id", e);
        }
    }


    @Override
    public boolean create(Tutor tutor) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_TUTOR, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, tutor.getUserId());
            statement.setString(2, tutor.getPhone());
            statement.setString(3, tutor.getEducation());
            statement.setString(4, tutor.getInfo());
            statement.setBigDecimal(5, tutor.getPricePerHour());
            boolean result = statement.executeUpdate() == 1;
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                tutor.setTutorId(resultSet.getInt(1));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Failed to create tutor", e);
        }
    }


    @Override
    public Tutor update(Tutor tutor) throws DaoException {
        Tutor oldTutor = findById(tutor.getTutorId())
                .orElseThrow(() -> new DaoException("Failed to update tutor, tutorId not found"));
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TUTOR)) {
            statement.setString(1, tutor.getPhone());
            statement.setString(2, tutor.getEducation());
            statement.setString(3, tutor.getInfo());
            statement.setBigDecimal(4, tutor.getPricePerHour());
            statement.setString(5, tutor.getRejectionMessage());
            statement.setInt(6,tutor.getTutorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to update tutor", e);
        }
        return oldTutor;
    }


}
