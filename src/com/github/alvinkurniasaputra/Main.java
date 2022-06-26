package com.github.alvinkurniasaputra;

import com.github.alvinkurniasaputra.database.Database;
import com.github.alvinkurniasaputra.database.ToDoHandler;
import com.github.alvinkurniasaputra.database.UserHandler;
import com.github.alvinkurniasaputra.menus.LoginMenu;
import com.github.alvinkurniasaputra.models.User;

public class Main {

    public static UserHandler USER_HANDLER;
    public static ToDoHandler TODO_HANDLER;

    public static void main(String[] args) {
        Database database = new Database();
        database.connect();

        USER_HANDLER  = new UserHandler(database);
        TODO_HANDLER  = new ToDoHandler(database);

//        USER_HANDLER.registerUser(new User(0,"admin", "admin123"));
//
//        USER_HANDLER.registerUser(new User(0,"alvian", "alvian123"));
//        USER_HANDLER.registerUser(new User(0,"julian", "julian123"));
//        USER_HANDLER.registerUser(new User(0,"lia", "lia123"));

        new LoginMenu();
    }

}

