package Input;

import dbManager.DbManager;
import dbManager.DbManagerImpl;

import java.sql.SQLException;
import java.util.List;

public class Action {
    private static final String CONNECTION_URL = "jdbc:sqlite:MyLib.db";
    private static final String DRIVER_CLASS = "org.sqlite.JDBC";

    private DbManager dbManager = new DbManagerImpl(CONNECTION_URL, DRIVER_CLASS);

    public void actionMake(String section, String action, Model model) throws SQLException {

        switch (action) {
            case "ADD":
                    dbManager.add(model, section);
                break;
            case "EDIT":
                    dbManager.edit(model, section);
                break;
            case "REMOVE":
                dbManager.remove(model, section);
                break;
            default:
                System.out.println("Non-action input.");
                help();
                break;
        }
    }

    public void showTable(String section) throws SQLException {
        List<Model> models = dbManager.listAll(section);
        for (Model model: models) {
            System.out.println(model.toString());
        }
    }

    public void help(){
        System.out.println("Please, make the correct enter:\n" +
                " section(for example BOOK)" +
                "\n\t -add -title -author -release data\n" +
                "\t -edit -title -author -release data\n" +
                "\t -remove -title -author -release data\n" +
                "\t -list\n" +
                " help\n" +
                " quit");
    }
}
