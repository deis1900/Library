import Input.Action;
import Input.Model;
import Input.Validator;

import java.sql.SQLException;

class Menu {
    private Action action = new Action();
    private Validator valid = new Validator();

    void choice(String str) throws SQLException {

        String[] strings = str.split(" -", 2);
// Input should be correct for the name of table

        if (valid.verifyNameOfTable(strings[0])) {

            String section = strings[0].toUpperCase();
            strings = str.split(" -", 5);
            String commandForAction = strings[1].toUpperCase();

// I filter the method list
            if (valid.filterCommandList(commandForAction)) {
                Model model = new Model(strings[2], strings[3], strings[4]);
                action.actionMake(section, commandForAction, model);

            } else {
                action.actionMake(section);
            }

        } else {
            System.out.println("This table is not exist");
            action.help();
        }
    }

}
