package com.example.asd.web.services;

import com.example.asd.data.entities.Task;
import com.example.asd.data.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService{
TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> readAll(){
        return taskRepository.findAll();
    }
    public Optional<Task> readById(Long id){
        return taskRepository.findById(id);
    }
    public List<Task> readByYearAndMonth(String year, String month){
        return taskRepository.findAllByYearAndMonth(Integer.parseInt(year), Integer.parseInt(month));
    }
    public List<Task> readByYear(int year){
        return taskRepository.findAllByYear(year);
    }
    public Task update(Long id, String name, int priority, String date, String time){
        int[] split = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();

        if (readById(id).isPresent()) {
            readById(id).map(task -> {
                task.setName(name);
                task.setPriority(priority);
                task.setYear(split[0]);
                task.setMonth(split[1]);
                task.setDay(split[2]);
                task.setDate(String.format("%s-%s-%s", split[0],
                        split[1]>9?String.valueOf(split[1]):"0"+String.valueOf(split[1]),
                        split[2]>9?String.valueOf(split[2]):"0"+String.valueOf(split[2])));
                task.setTime(time);
                return create(task);
            });
            return readById(id).get();
        }
        else {
            Task newTask = new Task(name, priority, split[0], split[1], split[2], time);
            newTask.setId(id);
            return create(newTask);
        }
    }
    public List<Task> readAllByDate(String date){
        return  taskRepository.findAllByDate(date);
    }
    public Task create(Task task){
        return taskRepository.save(task);
    }
    public Task create(String name, int priority, String date, String time) {

        int[] splitTaskDate = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();
        return  taskRepository.save(new Task(name, priority, splitTaskDate[0], splitTaskDate[1],
                splitTaskDate[2], time));
    }
    public void delete(Task task){
        taskRepository.delete(task);
    }

}
