import Input.Action;
import Input.Model;
import Input.Validator;

import java.sql.SQLException;

class Selection {
    private Action action = new Action();
    private Validator valid = new Validator();

    void choice(String str) throws SQLException {

        int amountOfDate = (str.length() - str.replace(" -", " ").length()) +1;
        String[] strings = str.split(" -", amountOfDate);

        if (valid.verifyNameOfTable(strings[0])) {

            String section = strings[0].toUpperCase();
            String commandForAction = strings[1].toUpperCase();

            if (amountOfDate == 2) {
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
