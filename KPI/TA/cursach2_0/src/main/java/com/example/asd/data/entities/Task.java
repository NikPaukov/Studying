package com.example.asd.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name="tasks")
public class Task {
    @Column
    private String name;

    @Column
    private int priority;

    @Column
    private int year;
    @Column
    private int month;
    @Column
    private int day;
    @Column
    private String time;
    @Column
    private String date;
    @Column
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Task(String name, int priority, int year, int month, int day, String time) {
        this.name = name;
        this.priority = priority;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.date=String.format("%s-%s-%s", year, month>9?String.valueOf(month):"0"+String.valueOf(month),
                day>9?String.valueOf(day):"0"+String.valueOf(day));
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }



    public Task() {

    }
}
