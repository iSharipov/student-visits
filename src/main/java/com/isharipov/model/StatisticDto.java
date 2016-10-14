package com.isharipov.model;

import java.util.Date;

/**
 * Created by Илья on 14.10.2016.
 */
public class StatisticDto {

    private final String name;
    private final String sirname;
    private final String group;
    private final String lesson;
    private final Date date;

    public StatisticDto(String name, String sirname, String group, String lesson, Date date) {
        this.name = name;
        this.sirname = sirname;
        this.group = group;
        this.lesson = lesson;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatisticDto that = (StatisticDto) o;

        return name.equals(that.name)
                && sirname.equals(that.sirname)
                && group.equals(that.group)
                && lesson.equals(that.lesson)
                && date.equals(that.date);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + sirname.hashCode();
        result = 31 * result + group.hashCode();
        result = 31 * result + lesson.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "StatisticDto{" +
                "name='" + name + '\'' +
                ", sirname='" + sirname + '\'' +
                ", group='" + group + '\'' +
                ", lesson='" + lesson + '\'' +
                ", date=" + date +
                '}';
    }

}
