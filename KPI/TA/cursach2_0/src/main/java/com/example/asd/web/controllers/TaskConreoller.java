package com.example.asd.web.controllers;

import com.example.asd.web.services.TaskService;
import com.example.asd.data.entities.Task;
import com.example.asd.utils.DateUtils;
import com.example.asd.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;

@Controller
@RequestMapping("/tasks")
public class TaskConreoller {

    @Autowired
    TaskService taskService;

    private void addDate(Model model, String taskDate) {
        if (taskDate.equals("today")) {
            model.addAttribute("date", DateUtils.getToday());
        } else if (taskDate.equals("tomorrow")) {
            model.addAttribute("date", DateUtils.getTomorrow());
        } else {
            model.addAttribute("date", taskDate);
        }

    }

    private void redirect(Model model, String tasksDate) {
        if (tasksDate.equals("all")) {
            model.addAttribute("tasks", taskService.readAll());
        } else if (tasksDate.equals("today")) {
            model.addAttribute("tasks", taskService.readAllByDate(DateUtils.getToday()));
        } else if (tasksDate.equals("tomorrow")) {
            model.addAttribute("tasks", taskService.readAllByDate(DateUtils.getTomorrow()));
        } else if (tasksDate.equals("this-month")) {
            String[] datemonth = DateUtils.getThisMonth();
            model.addAttribute("tasks", taskService.readByYearAndMonth(datemonth[0], datemonth[1]));
        } else if (DateUtils.isYYYYMMDD(tasksDate)) {
            model.addAttribute("tasks", taskService.readAllByDate(tasksDate));
        } else if (DateUtils.isYYYYMM(tasksDate)) {
            String[] splitPageDate = tasksDate.split("-");
            model.addAttribute("tasks", taskService.readByYearAndMonth(splitPageDate[0], splitPageDate[1]));
        } else if (DateUtils.isYYYY(tasksDate)) {
            model.addAttribute("tasks", taskService.readByYear(Integer.parseInt(tasksDate)));
        } else {
            throw new IncorrectDateException(tasksDate);
        }
    }


    @GetMapping("/choosedate")
    public String showMenu() {
        return "choose-date";
    }

    @GetMapping("/{tasksDate}")
    public String showTasks(@PathVariable String tasksDate, Model model) {
        redirect(model, tasksDate);
        addDate(model, tasksDate);
        return "tasks";
    }

    @PostMapping("/{tasksDate}")
    public String getTasks(
            @PathVariable String tasksDate,
            @RequestParam String name,
            @RequestParam int priority,
            @RequestParam String date,
            @RequestParam String time, Model model) {

        taskService.create(name, priority, date, time);
        redirect(model, tasksDate);
        addDate(model, tasksDate);
        return "tasks";
    }

    @PostMapping("/{tasksDate}/d")
    RedirectView deleteTask(@PathVariable String tasksDate,
                            @RequestParam Long id, Model model) {
        Task task = taskService.readById(id).orElseThrow(() -> new NoSuchTaskException(id));
        taskService.delete(task);
        redirect(model, tasksDate);
        addDate(model, tasksDate);
        return new RedirectView("/tasks/" + tasksDate);
    }

    @PostMapping("/{tasksDate}/r")
    RedirectView redactTask(@PathVariable String tasksDate,
                            @RequestParam Long id, @RequestParam String name,
                            @RequestParam int priority,
                            @RequestParam String date,
                            @RequestParam String time,
                            Model model) {
        taskService.update(id, name, priority, date, time);
        redirect(model, tasksDate);
        addDate(model, tasksDate);
        return new RedirectView("/tasks/" + tasksDate);
    }
}
