package dbManager;

import Input.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManagerImpl implements DbManager {
    private static final String CONNECTIONURL = "jdbc:sqlite:MyLib.db";
    private Connection connection = null;
    private Statement stmt = null;

    private void open(String tableName) {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(CONNECTIONURL);

            createTable(tableName);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
    }

    private void createTable(String tableName) throws SQLException {
        stmt = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " " +
                "(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "Name TEXT NOT NULL," +
                "Date_of_release TEXT, " +
                "Author TEXT)";
        stmt.executeUpdate(sql);
        stmt.close();
    }

    private Boolean checkRow(String name, String tableName) {

        boolean b;
        b = true;
        try {
            String sql = "SELECT * FROM " + tableName + ";";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("Name") != null && rs.getString("Name").equals(name)) {
                    b = false;
                }
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return b;
    }

    private void close() {
        try {

            if (connection != null) {
                stmt.close();
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void add(Model model, String tableName) throws SQLException {
        open(tableName);
        stmt = connection.createStatement();
        if (checkRow(model.getName(), tableName)) {
            String sql = "INSERT INTO " + tableName + " (Name, Author, Date_of_release) " +
                    " VALUES (' " + model.getName() + " ', ' " + model.getAuthor() + " ', '" + model.getDate() + "');";
            int rows = stmt.executeUpdate(sql);
            if (rows == 1) {
                System.out.println(tableName + " " + model.getName() + " added");
            } else {
                System.out.println(tableName + " row not added");
            }

        } else {
            System.out.println("In the table there is such record");
        }

        close();

    }

    @Override
    public void remove(Model model, String tableName) throws SQLException {

        open(tableName);
        if (checkRow(model.getName(), tableName)) {
            stmt = connection.createStatement();
            String sql = "DELETE FROM " + tableName + " WHERE Name = ' " + model.getName() + " '" +
                    " AND Date_of_release = '" + model.getDate() + "';";
            int rows = stmt.executeUpdate(sql);
            if (rows == 1) {
                System.out.println(tableName + " " + model.getName() + " was removed.");
            } else {
                System.out.println(tableName + " row not removed");
            }
        } else {
            System.out.println("There is no such record in the table");
        }

        close();
    }

    @Override
    public void edit(Model model, String tableName) throws SQLException {

        open(tableName);
        stmt = connection.createStatement();

        if (checkRow(model.getName(), tableName)) {
            String sql = "UPDATE " + tableName + " SET Date_of_release = ' " + model.getDate() + " '" +
                    "WHERE Name= ' " + model.getName() + " '; ";
            int rows = stmt.executeUpdate(sql);
            if (rows == 1) {
                System.out.println(tableName + " " + model.getName() + " was edited.");
            } else {
                System.out.println(tableName + " row not edited");
            }
        } else {
            System.out.println("Table have this record.");
        }

        close();
    }

    @Override
    public List<Model> listAll(String tableName) throws SQLException {
        open(tableName);

        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + ";");
        List<Model> models = new ArrayList<>();

        while (rs.next()) {
            Model model = new Model(rs.getLong("Id"), rs.getString("Name"), rs.getString("Date_of_release"),
                    rs.getString("Author"));
            models.add(model);
        }

        close();
        return models;
    }
}
