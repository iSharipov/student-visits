package com.isharipov.dao;

import com.isharipov.model.Student;

import java.util.List;

/**
 * Created by Илья on 14.10.2016.
 */
public interface StudentDao {
    List<Student> findAllStudents();

    Student save(Student student);
}
