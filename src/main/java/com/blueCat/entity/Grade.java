package com.blueCat.entity;

import java.util.ArrayList;
import java.util.List;

public class Grade {
    private Long id;
    private String name;
    private List<Student> users = new ArrayList<>();

    private Student user;

    // 省略 setter、getter

    public void addStudent(Student student) {
        users.add(student);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getUsers() {
        return users;
    }

    public void setUsers(List<Student> users) {
        this.users = users;
    }

    public Student getUser() {
        return user;
    }

    public void setUser(Student user) {
        this.user = user;
    }
}
