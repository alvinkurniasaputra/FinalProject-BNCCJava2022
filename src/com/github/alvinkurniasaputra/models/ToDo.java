package com.github.alvinkurniasaputra.models;

public class ToDo {

    private final int id, userId;
    private String content, time;
    private int isCompleted;

    public ToDo(int id, int userId, String content, String time, int isCompleted) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.time = time;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int isCompleted() {
        return isCompleted;
    }

    public void setCompleted(int completed) {
        isCompleted = completed;
    }
}
