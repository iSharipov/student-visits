package com.isharipov;

import com.isharipov.dao.*;
import com.isharipov.model.Lesson;
import com.isharipov.model.StatisticDto;
import com.isharipov.model.Student;
import com.isharipov.model.StudentVisit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Илья on 14.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "admin", "secret");) {
            createTable(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS students");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS lessons");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS student_visits");

        connection.createStatement().executeUpdate("CREATE TABLE students" +
                "(" +
                "id int auto_increment," +
                "name varchar(255)," +
                "sirname varchar(255)," +
                "group_name varchar(255)," +
                "primary key(id)" +
                ");");

        connection.createStatement().executeUpdate("CREATE TABLE lessons" +
                "(" +
                "id int auto_increment," +
                "lesson varchar(255)," +
                "date DATE " +
                ");");

        connection.createStatement().executeUpdate("CREATE TABLE student_visits" +
                "(" +
                "student_id int," +
                "lesson_id int," +
                "FOREIGN KEY (student_id) REFERENCES students(id)," +
                "FOREIGN KEY (lesson_id) REFERENCES lessons(id)" +
                ");");

        StudentDao studentDao = new StudentDaoImpl(connection);
        Student student = new Student(null, "Ivan", "Ivanov", "C-51");
        student = studentDao.save(student);
//        System.out.println(student.getId());

        LessonDao lessonDao = new LessonDaoImpl(connection);
        Lesson lesson = new Lesson("DB", new Date());
        lessonDao.save(lesson);
//        System.out.println(lesson.getId());

        StudentVisitDao studentVisitDao = new StudentVisitDaoImpl(connection);
        StudentVisit studentVisit = new StudentVisit(student.getId(), lesson.getId());

        Student student2 = new Student(null, "Ivan", "Ivanov", "C-52");
        student2 = studentDao.save(student2);
        StudentVisit studentVisit2 = new StudentVisit(student2.getId(), lesson.getId());
        studentVisitDao.save(studentVisit);
        studentVisitDao.save(studentVisit2);

        VisitDao visitDao = new VisitDaoImpl(connection);

        List<StatisticDto> allWithFilters = visitDao.findAllWithFilters(null, null, null, null, null, null);
        allWithFilters.stream().forEach(s -> System.out.println(s));
    }
}
