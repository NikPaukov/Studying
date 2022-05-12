package com.example.springtest1.JSONGEtter;

import java.util.List;

public class Greeting {
    public static void main(String[] args) {
        System.out.println(String.join(" ", List.of("asd", "asd", "123", "zxc")));
    }
    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }

    private final long id;
    private final String content;
}


