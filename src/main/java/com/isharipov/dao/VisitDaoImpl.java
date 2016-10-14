package com.isharipov.dao;

import com.isharipov.model.StatisticDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Илья on 14.10.2016.
 */
public class VisitDaoImpl extends BaseDao implements VisitDao {

    public static String findAllQuery = "SELECT students.name, students.sirname, students.group_name," +
            " lessons.lesson, lessons.date" +
            " FROM students" +
            " INNER JOIN student_visits" +
            " ON students.id = student_visits.student_id" +
            " INNER JOIN lessons ON student_visits.lesson_id = lessons.id" +
            " WHERE 1=1 ";

    public VisitDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<StatisticDto> findAllWithFilters(Integer studentId, String studentName, String studentSirname, String group, String lesson, Date lessonDate) {
        return execute(studentId, studentName, studentSirname, group, lesson, lessonDate);
    }

    @Override
    public void addVisit(int studentId, int lessonId) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO student_visits (student_id, lesson_id) VALUES(?, ?)");

            statement.setInt(1, studentId);
            statement.setInt(2, lessonId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteVisit(int studentId, int lessonId) {

        try {
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM student_visits WHERE student_id = ? AND lesson_id = ?");
            statement.setInt(1, studentId);
            statement.setInt(2, lessonId);

            int i = statement.executeUpdate();
            System.out.println("Удалены " + i + " записей");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVisitByGroupName(String group) {
        try {
            getConnection().setAutoCommit(false);
            PreparedStatement statement = getConnection().prepareStatement("SELECT students.id FROM students WHERE students.group = ?");

            statement.setString(1, group);

            ResultSet resultSet = statement.executeQuery();
            List<Integer> ids = new ArrayList<>();
            while (resultSet.next()) {
                ids.add(resultSet.getInt("id"));
            }

            PreparedStatement cityStatement = getConnection().prepareStatement("DELETE FROM student_visits WHERE student_id IN(?)");
            cityStatement.setArray(1, getConnection().createArrayOf("INT", ids.toArray(new Integer[ids.size()])));
            getConnection().commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StatisticDto> findStatisticByStudentId(int studentId) {
        return execute(studentId, null, null, null, null, null);
    }

    @Override
    public List<StatisticDto> findStatisticByLessonName(String lessonName) {
        return execute(null, null, null, null, lessonName, null);
    }

    @Override
    public List<StatisticDto> findStatisticByLessonDate(Date lessonDate) {
        return execute(null, null, null, null, null, lessonDate);
    }

    @Override
    public List<StatisticDto> findStatisticByStudentName(String studentName) {
        return execute(null, studentName, null, null, null, null);
    }

    @Override
    public List<StatisticDto> findStatisticByStudentSirname(String studentSirname) {
        return execute(null, null, studentSirname, null, null, null);
    }

    @Override
    public List<StatisticDto> findStatisticByGroup(String group) {
        return execute(null, null, null, group, null, null);
    }

    private List<StatisticDto> execute(Integer studentId, String studentName, String studentSirname, String group, String lesson, Date lessonDate) {
        String additionalQuery;
        if (studentId != null) {
            additionalQuery = " AND students.id = " + studentId;
            findAllQuery += additionalQuery;
        }

        if (studentName != null) {
            additionalQuery = " AND students.name  LIKE '" + studentName + "\'";
            findAllQuery += additionalQuery;
        }

        if (studentSirname != null) {
            additionalQuery = " AND students.sirname LIKE '" + studentSirname + "\'";
            findAllQuery += additionalQuery;
        }

        if (group != null) {
            additionalQuery = " AND students.group_name LIKE \'" + group + "\'";
            findAllQuery += additionalQuery;
        }

        if (lesson != null) {
            additionalQuery = " AND lessons.lesson  LIKE '" + lesson + "\'";
            findAllQuery += additionalQuery;
        }

        if (lessonDate != null) {
            additionalQuery = " AND lessons.date = " + lessonDate;
            findAllQuery += additionalQuery;
        }

        try {
            List<StatisticDto> statisticDtos = new ArrayList<>();
            Statement stmt = getConnection().createStatement();
            ResultSet resultSet = stmt.executeQuery(findAllQuery);
            while (resultSet.next()) {
                statisticDtos.add(new StatisticDto(
                        resultSet.getString("name"),
                        resultSet.getString("sirname"),
                        resultSet.getString("group_name"),
                        resultSet.getString("lesson"),
                        resultSet.getDate("date")
                ));
            }
            return statisticDtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
