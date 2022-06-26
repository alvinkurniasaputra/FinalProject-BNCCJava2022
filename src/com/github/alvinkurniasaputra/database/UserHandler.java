package com.github.alvinkurniasaputra.database;

import com.github.alvinkurniasaputra.models.User;

import java.sql.ResultSet;

public class UserHandler {

    private final Database db;

    public UserHandler(Database db) {
        this.db = db;
    }

    public void registerUser(User user) {
        db.execute("INSERT INTO user (name, password) VALUES (?, ?);",
                user.getUsername(), user.getPassword());
    }

    public boolean doesUserExists(String username) {
        try (ResultSet set = db.getResults("SELECT * FROM user WHERE name = ?;", username)) {
            return set.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User findUser(String targetUsername) {
        try (ResultSet set = db.getResults("SELECT * FROM user WHERE name = ?;", targetUsername)) {
            if (set.next()) {
                int id = set.getInt("id");
                String username = set.getString("name");
                String password = set.getString("password");

                return new User(id, username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
