package dbManager;

import model.Model;

import java.sql.SQLException;
import java.util.List;

public interface DbManager {

    void  add(String name, String date, String author, String tableName) throws SQLException;

    void remove(String name, String  tableName) throws SQLException;

    void edit(String name, String date, String author, String  tableName) throws SQLException;

    List<Model> listAll(String tableName) throws SQLException;

}
