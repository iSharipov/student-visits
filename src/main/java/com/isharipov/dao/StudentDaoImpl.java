package com.isharipov.dao;

import com.isharipov.model.Student;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Илья on 14.10.2016.
 */
public class StudentDaoImpl extends BaseDao<Student > implements StudentDao {

    public StudentDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Student> findAllStudents() {
        return null;
    }

    @Override
    public Student save(Student student) {
        return super.save(student);
    }

}
