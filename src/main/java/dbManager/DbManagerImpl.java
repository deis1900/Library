package dbManager;

import model.*;

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
                "Date TEXT, " +
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
                if (rs.getString("Name")!= null && rs.getString("Name").equals(name)) {
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

    public void add(String name, String date, String author, String tableName) throws SQLException {
        open(tableName);
        stmt = connection.createStatement();
        if (checkRow(name, tableName)) {
            String sql = "INSERT INTO " + tableName + " (Name, Date, Author) " +
                    " VALUES (' " + name + " ', ' " + date + " ', '" + author + "');";
            int rows = stmt.executeUpdate(sql);
            if (rows == 1) {
                System.out.println(tableName + " " + name + " added");
            }else {
                System.out.println(tableName + " row not added");
            }

        } else {
            System.out.println("In the table there is such record");
        }

        close();

    }

    public void remove(String name, String tableName) throws SQLException {

        open(tableName);
        if (checkRow(name, tableName)) {
            stmt = connection.createStatement();
            String sql = "DELETE FROM " + tableName + " WHERE Name = ' " + name + " ';";
            int rows = stmt.executeUpdate(sql);
            if (rows == 1) {
                System.out.println(tableName + " " + name + " was removed.");
            }else {
                System.out.println(tableName + " row not removed");
            }
        } else {
            System.out.println("There is no such record in the table");
        }

        close();
    }

    public void edit(String name, String date, String author, String tableName) throws SQLException {

        open(tableName);
        stmt = connection.createStatement();

        if (checkRow(name, tableName)) {
            String sql = "UPDATE " + tableName + " SET Date = ' " + date + " '" +
                    "WHERE Name= ' " + name + " '; ";
            int rows = stmt.executeUpdate(sql);
            if (rows ==1){
                System.out.println(tableName + " " + name + " was edited.");
            }else {
                System.out.println(tableName +  " row not edited");
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
        List<Model> models= new ArrayList<>();

        while (rs.next()) {
            Model model= new Model(rs.getLong("Id"), rs.getString("Name"), rs.getString("Date"),
                    rs.getString("Author"));
            models.add(model);
        }

        close();
        return models;
    }
}
