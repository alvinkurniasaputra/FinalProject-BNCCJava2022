package com.github.alvinkurniasaputra.models;

import java.util.ArrayList;

public class User {

    private final int id;
    private final String username, password;
    private ArrayList<ToDo> todotemp = new ArrayList<>();
//    private ArrayList<ToDo> edittodotemp = new ArrayList<>();
    private ArrayList<Integer> edittodoid = new ArrayList<>();
    private ArrayList<String> edittodocontent = new ArrayList<>();
    private ArrayList<String> edittodotime = new ArrayList<>();
    // 5  2 (3,4)
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<ToDo> getTodotemp() {
        return todotemp;
    }

//    public ArrayList<ToDo> getEditTodotemp() {
//        return edittodotemp;
//    }

    public ArrayList<Integer> getEditTodoid() {
        return edittodoid;
    }

    public ArrayList<String> getEditTodocontent() {
        return edittodocontent;
    }

    public ArrayList<String> getEditTodotime() {
        return edittodotime;
    }

    public void addTodotemp(ToDo todotemp) {
        this.todotemp.add(todotemp);
    }

//    public void addEditTodotemp(ToDo todotemp) {
//        this.edittodotemp.add(todotemp);
//    }

//    public void addEditTodotemp(ArrayList<ToDo> todotemp) {
//        this.edittodotemp.addAll(todotemp);
//    }

    public void addEditTodoid(int id) {
        this.edittodoid.add(id);
    }

    public void addEditTodocontent(String content) {
        this.edittodocontent.add(content);
    }

    public void addEditTodotime(String time) {
        this.edittodotime.add(time);
    }

    public void setTodotemp(int i, ToDo todotemp) {
        this.todotemp.set(i, todotemp);
    }

    public void setEditTodoid(int i, int id) {
        this.edittodoid.set(i, id);
    }

    public void setEditTodocontent(int i, String content) {
        this.edittodocontent.set(i, content);
    }

    public void setEditTodotime(int i, String time) {
        this.edittodotime.set(i, time);
    }

//    public void setEditTodotemp(int i, String content, String time) {
//        this.edittodotemp.get(i).setContent(content);
//        this.edittodotemp.get(i).setTime(time);
//    }

    public void deleteTodotemp() {
        this.todotemp.clear();
    }

    public void removeTodotemp(int i) {
        this.todotemp.remove(i);
    }

    public void removeEditTodoid(int i) {
        this.edittodoid.remove(i);
    }

    public void removeEditTodocontent(int i) {
        this.edittodocontent.remove(i);
    }

    public void removeEditTodotime(int i) {
        this.edittodotime.remove(i);
    }

//    public void deleteEditTodotemp() {
//        this.edittodotemp.clear();
//        this.edittodoid.clear();
//        this.edittodocontent.clear();
//        this.edittodotime.clear();
//    }

}
