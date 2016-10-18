package com.isharipov.dao;

import com.isharipov.annotations.Attribute;
import com.isharipov.annotations.Table;
import com.isharipov.exceptions.NoTableFoundException;
import com.isharipov.model.Lesson;
import com.isharipov.model.Student;
import com.isharipov.model.StudentVisit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;

/**
 * Created by Илья on 14.10.2016.
 */
public abstract class BaseDao<T> {

    private static final String STUDENT_INSERT_QUERY = "INSERT INTO students (name, sirname, group_name) values(?, ?, ?)";
    private static final String LESSON_INSERT_QUERY = "INSERT INTO lessons (lesson, date) values(?, ?)";
    private static final String STUDENT_VISIT_INSERT_QUERY = "INSERT INTO student_visits (student_id, lesson_id) values(?, ?)";

    private final Connection connection;

    protected BaseDao(Connection connection) {
        this.connection = connection;
    }

    protected Connection getConnection() {
        return this.connection;
    }

    protected T save(T obj) {
        PreparedStatement statement;

        Class clazz = obj.getClass();
        String tableName;
        String columnName;
        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = (Table) clazz.getAnnotation(Table.class);
            tableName = table.name().toUpperCase();
        } else {
            tableName = clazz.getName().toUpperCase();
        }

        try {
            statement = connection.prepareStatement("SELECT COLUMN_NAME, TYPE_NAME" +
                    " FROM INFORMATION_SCHEMA.COLUMNS" +
                    " WHERE TABLE_NAME = " + "'" + tableName + "'");
            ResultSet metadataQuery = statement.executeQuery();
            while (metadataQuery.next()) {
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Attribute.class)) {
                        Attribute attribute = field.getAnnotation(Attribute.class);
                        columnName = attribute.name();
                    }else{

                    }
                }
//                System.out.println(metadataQuery.getString(1));
//                System.out.println(metadataQuery.getString(2));
            }
        } catch (SQLException e) {
            throw new NoTableFoundException(e.getMessage());
        }

        Method[] declaredMethods = clazz.getDeclaredMethods();

        if (obj instanceof Student) {
            Student student = (Student) obj;
            try {
                statement = connection.prepareStatement(STUDENT_INSERT_QUERY);
                statement.setString(1, student.getName());
                statement.setString(2, student.getSirname());
                statement.setString(3, student.getGroup());
                statement.executeUpdate();
                int affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    throw new RuntimeException("Creating user failed, no rows affected.");
                }
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        student = student.setId(generatedKeys.getInt(1));
                    } else {
                        throw new RuntimeException("Creating student failed, no ID obtained.");
                    }
                }
                return (T) student;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (obj instanceof Lesson) {
            Lesson lesson = (Lesson) obj;
            try {
                statement = connection.prepareStatement(LESSON_INSERT_QUERY);
                statement.setString(1, lesson.getLesson());
                statement.setDate(2, new Date(lesson.getDate().getTime()));
                int affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    throw new RuntimeException("Creating lesson failed, no rows affected.");
                }
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        lesson.setId(generatedKeys.getInt(1));
                    } else {
                        throw new RuntimeException("Creating lesson failed, no ID obtained.");
                    }
                }
                return (T) lesson;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (obj instanceof StudentVisit) {
            StudentVisit studentVisit = (StudentVisit) obj;
            try {
                statement = connection.prepareStatement(STUDENT_VISIT_INSERT_QUERY);
                statement.setInt(1, studentVisit.getStudentId());
                statement.setInt(2, studentVisit.getLessonId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return obj;
    }
}
