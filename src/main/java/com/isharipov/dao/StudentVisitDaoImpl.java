package com.isharipov.dao;

import com.isharipov.model.StudentVisit;

import java.sql.Connection;

/**
 * Created by Илья on 14.10.2016.
 */
public class StudentVisitDaoImpl extends BaseDao<StudentVisit> implements StudentVisitDao {

    public StudentVisitDaoImpl(Connection connection) {
        super(connection);
    }

    public StudentVisit save(StudentVisit studentVisit) {
        return super.save(studentVisit);
    }
}
