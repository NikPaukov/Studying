package com.example.asd.data.repositories;

import com.example.asd.data.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByDate(String Date);
    List<Task> findAllByYearAndMonth(int year,int month);
    List<Task> findAllByYear(int year);
}
