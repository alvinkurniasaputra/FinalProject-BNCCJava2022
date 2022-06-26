package com.github.alvinkurniasaputra.database;

import com.github.alvinkurniasaputra.models.ToDo;
import com.github.alvinkurniasaputra.models.User;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ToDoHandler {

    private final Database db;

    public ToDoHandler(Database db) {
        this.db = db;
    }

    public void addToDo(ToDo todo, User user) {
        db.execute("INSERT INTO todo (user_id, content, expire_time, is_completed) VALUES (?, ?, ?, ?);",
                user.getId(), todo.getContent(), todo.getTime(), todo.isCompleted());
    }

    public void editIsCompleted(ToDo todo) {
        db.execute("UPDATE todo SET is_completed = ? WHERE id = ?;",
                todo.isCompleted(), todo.getId());
    }

    public void editContent(String content, int id) {
        db.execute("UPDATE todo SET content = ? WHERE id = ?;",
                content, id);
    }

    public void editTime(String content, int id) {
        db.execute("UPDATE todo SET expire_time = ? WHERE id = ?;",
                content, id);
    }

    public void deleteToDo(ToDo todo) {
        db.execute("DELETE FROM todo WHERE id = ?;",
                todo.getId());
    }

    public ArrayList<ToDo> getToDo(User user) {
        ArrayList<ToDo> toDoList = new ArrayList<>();

        try (ResultSet set = db.getResults("SELECT * FROM todo WHERE user_id = "+user.getId()+" ;")) {
            while (set.next()) {
                int id = set.getInt("id");
                int userId = set.getInt("user_id");
                String content = set.getString("content");
                String time = set.getString("expire_time");
                int isCompleted = set.getInt("is_completed");

                ToDo toDo = new ToDo(id, userId, content, time, isCompleted);
                toDoList.add(toDo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toDoList;
    }


}
