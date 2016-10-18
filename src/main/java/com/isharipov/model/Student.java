package com.isharipov.model;

import com.isharipov.annotations.Attribute;
import com.isharipov.annotations.Table;

/**
 * Created by Илья on 14.10.2016.
 */
@Table(name = "students")
public class Student {
    @Attribute(name = "id")
    private final Integer id;

    @Attribute(name = "name")
    private final String name;

    @Attribute(name = "sirname")
    private final String sirname;

    @Attribute(name = "group")
    private final String group;

    public Student(Integer id, String name, String sirname, String group) {
        this.id = id;
        this.name = name;
        this.sirname = sirname;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public Student setId(Integer id) {
        return new Student(id, this.name, this.sirname, this.group);
    }

    public String getName() {
        return name;
    }

    public String getSirname() {
        return sirname;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && name.equals(student.name) && sirname.equals(student.sirname) && group.equals(student.group);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + sirname.hashCode();
        result = 31 * result + group.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sirname='" + sirname + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
