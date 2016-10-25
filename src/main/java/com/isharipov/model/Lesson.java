package com.isharipov.model;

import com.isharipov.annotations.Attribute;
import com.isharipov.annotations.Table;

import java.util.Date;

/**
 * Created by Илья on 14.10.2016.
 */
@Table(name = "lessons")
public class Lesson {
    @Attribute(name = "lesson")
    private final String lesson;

    @Attribute(name = "date")
    private final Date date;

    @Attribute(name = "id")
    private int id;

    public Lesson(String lesson, Date date) {
        this.lesson = lesson;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLesson() {
        return lesson;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson1 = (Lesson) o;
        return id == lesson1.id && lesson.equals(lesson1.lesson) && date.equals(lesson1.date);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + lesson.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lesson='" + lesson + '\'' +
                ", date=" + date +
                '}';
    }
}
