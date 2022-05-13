package com.example.cursach.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task {
    public String getPriority() {
        return priority;
    }

    public String getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    private final String name;
    private final String priority;
    private final String duration;


    public Task(String name, String priority, String duration) {
        this.name = name.trim();
        this.priority = priority.trim();
        this.duration = duration.trim();
    }
    public static List<Task> convertXMLListtoTaskList(List<String[]> ls){
        List<Task> res = new ArrayList<>();
        for(String[] taskString: ls){
            res.add(new Task(taskString[0],taskString[1],taskString[2]));
        }
        return res;
    }


    public String toXml(){
        return  "    <task>" + "\n" +
                "        <name>" + name + "</name>" + "\n" +
                "        <priority>" + priority + "</priority>" + "\n" +
                "        <duration>" + duration + "</duration>" + "\n" +
                "    </task>" + "\n\n";
    }
}
