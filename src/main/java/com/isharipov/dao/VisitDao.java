package com.isharipov.dao;

import com.isharipov.model.StatisticDto;

import java.util.Date;
import java.util.List;

/**
 * Created by Илья on 14.10.2016.
 */
public interface VisitDao {
    List<StatisticDto> findStatisticByStudentId(int studentId);

    List<StatisticDto> findAllWithFilters(Integer studentId, String studentName, String studentSirname, String group, String lesson, Date lessonDate);

    void addVisit(int studentId, int lessonId);

    void deleteVisit(int studentId, int lessonId);

    void deleteVisitByGroupName(String group);

    List<StatisticDto> findStatisticByLessonName(String lessonName);

    List<StatisticDto> findStatisticByLessonDate(Date lessonDate);

    List<StatisticDto> findStatisticByStudentName(String studentName);

    List<StatisticDto> findStatisticByStudentSirname(String studentSirname);

    List<StatisticDto> findStatisticByGroup(String group);
}
