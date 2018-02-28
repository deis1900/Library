package dbManager;

import Input.Model;

import java.sql.SQLException;
import java.util.List;

public interface DbManager {

    void  add(Model model, String tableName) throws SQLException;

    void remove(Model model, String  tableName) throws SQLException;

    void edit(Model model, String  tableName) throws SQLException;

    List<Model> listAll(String tableName) throws SQLException;

}
