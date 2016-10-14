package com.isharipov.dao;

import com.isharipov.model.Lesson;

import java.util.List;

/**
 * Created by Илья on 14.10.2016.
 */
public interface LessonDao {
    List<Lesson> findAllLessons();

    Lesson save(Lesson lesson);
}
