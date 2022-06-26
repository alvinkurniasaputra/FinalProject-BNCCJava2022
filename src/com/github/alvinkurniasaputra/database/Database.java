package com.github.alvinkurniasaputra.database;

import java.sql.*;

public class Database {

    private Connection connection;

    public ResultSet getResults(String sql, Object ...args) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            statement.setObject(i + 1, args[i]);
        }

        return statement.executeQuery();
    }

    public boolean execute(String sql, Object ...args) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void connect() {
        String url = "jdbc:mysql://localhost:3306/final-project-java";

        try {
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Database berhasil terkoneksi");
        } catch (SQLException e) {
            System.out.println("Database gagal terkoneksi");
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
