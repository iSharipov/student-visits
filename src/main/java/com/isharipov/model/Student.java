package com.isharipov.model;

/**
 * Created by Илья on 14.10.2016.
 */
public class Student {
    private final Integer id;
    private final String name;
    private final String sirname;
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
