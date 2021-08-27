package com.java.tutorials.core.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExample {

    public static void main(String[] args) throws ClassNotFoundException {
        // It was needed to load driver before usage prior to Java 6
        Class.forName("org.h2.Driver");

        String url = "jdbc:h2:~/test"; //database specific url (From H2 documentation, embedded connection).
        String user = "sa";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            DatabaseMetaData dbMeta = connection.getMetaData();
            System.out.println("DB Metadata: " + dbMeta.toString());

            deletePeopleTable(connection);
            createPeopleTable(connection);
            addSomePeople(connection);
            queryAllPeople(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deletePeopleTable(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String sql = "DROP TABLE people";
            stmt.executeUpdate(sql);
        }
    }

    private static void createPeopleTable(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE people " +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        }
    }

    private static void addSomePeople(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql1 = "INSERT INTO people VALUES (1, 'Zara', 18)";
            statement.executeUpdate(sql1);
            String sql2 = "INSERT INTO people VALUES (2, 'Ali', 20)";
            statement.executeUpdate(sql2);
        }
    }

    private static void queryAllPeople(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM people";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String age = resultSet.getString("age");
                    System.out.printf("name=%s, age=%s \n", name, age);
                }
            }
        }
    }
}
