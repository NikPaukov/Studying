package com.example.test1;

import javax.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String date;
    private String name;

    @Override
    public String   toString() {
        return "Task{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Task() {
    }

    public Task(String date, String name) {
        this.date = date;
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
