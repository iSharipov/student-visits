package com.isharipov.model;

/**
 * Created by Илья on 14.10.2016.
 */
public class StudentVisit {
    private final int studentId;
    private final int lessonId;

    public StudentVisit(int studentId, int lessonId) {
        this.studentId = studentId;
        this.lessonId = lessonId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getLessonId() {
        return lessonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentVisit that = (StudentVisit) o;

        if (studentId != that.studentId) return false;
        return lessonId == that.lessonId;

    }

    @Override
    public int hashCode() {
        int result = studentId;
        result = 31 * result + lessonId;
        return result;
    }

    @Override
    public String toString() {
        return "StudentVisit{" +
                "studentId=" + studentId +
                ", lessonId=" + lessonId +
                '}';
    }
}
