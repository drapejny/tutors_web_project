package by.slizh.tutorsweb.model.dao.impl;

import by.slizh.tutorsweb.entity.Subject;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.dao.ColumnName;
import by.slizh.tutorsweb.model.dao.SubjectDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SubjectDaoImpl extends SubjectDao {

    private static final String SQL_FIND_ALL_SUBJECTS = """
            SELECT subject_id, subject_name
            FROM subjects;
            """;
    private static final String SQL_FIND_SUBJECT_BY_ID = """
            SELECT subject_id, subject_name
            FROM subjects
            WHERE subject_id = ?;
            """;
    private static final String SQL_DELETE_SUBJECT_BY_ID = """
            DELETE FROM subjects
            WHERE subject_id = ?;
            """;
    private static final String SQL_CREATE_SUBJECT = """
            INSERT INTO subjects (subject_name)
            VALUES (?);
            """;
    private static final String SQL_UPDATE_SUBJECT = """
            UPDATE subjects
            SET subject_name = ?
            WHERE subject_id = ?;
            """;

    @Override
    public List<Subject> findAll() throws DaoException {
        List<Subject> subjects = new LinkedList<Subject>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_SUBJECTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(resultSet.getInt(ColumnName.SUBJECT_ID));
                subject.setSubjectName(resultSet.getString(ColumnName.SUBJECT_NAME));
                subjects.add(subject);
            }
            return subjects;
        } catch (SQLException e) {
            throw new DaoException("Failed to find all subjects", e);
        }
    }

    @Override
    public Optional<Subject> findById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_SUBJECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(resultSet.getInt(ColumnName.SUBJECT_ID));
                subject.setSubjectName(resultSet.getString(ColumnName.SUBJECT_NAME));
                return Optional.of(subject);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find subject by id", e);
        }
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SUBJECT_BY_ID)) {
            statement.setInt(1, id);
            boolean result = statement.executeUpdate() == 1;
            return result;
        } catch (SQLException e) {
            throw new DaoException("Failed to delete subject by if", e);
        }
    }

    @Override
    public boolean create(Subject subject) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_SUBJECT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, subject.getSubjectName());
            boolean result = statement.executeUpdate() == 1;
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                subject.setSubjectId(resultSet.getInt(1));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Failed to create subject", e);
        }
    }

    @Override
    public Subject update(Subject subject) throws DaoException {
        Subject oldSubject = findById(subject.getSubjectId())
                .orElseThrow(() -> new DaoException("Failed to update subject, subjectId not found"));
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SUBJECT)) {
            statement.setString(1, subject.getSubjectName());
            statement.setInt(2, subject.getSubjectId());
            statement.executeUpdate();
            return oldSubject;
        } catch (SQLException e) {
            throw new DaoException("Failed to update subject", e);
        }
    }
}
