import Input.Action;
import Input.Model;
import Input.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

class Selection {
    Logger logger = LoggerFactory.getLogger(Selection.class.getName());
    private Action action = new Action();
    private Validator valid = new Validator();

    Boolean choice(String string) throws SQLException {
        String str = valid.validateInput(string);

        int amountOfInput = (str.length() - str.replace(" -", " ").length()) +1;
        String[] strings = str.split(" -", amountOfInput);


        if (valid.checkForHelpCommand(strings[0])) {
            action.help();
            return false;
        } else if (valid.checkForQuitCommand(strings[0])) {
                System.exit(0);
            }

        String section = strings[0].toUpperCase();
        if (valid.validNameOfTable(section)) {

            String commandForAction = strings[1].toUpperCase();
            if (valid.validCommand(commandForAction)) {

                if (amountOfInput == 2) {
                    action.showTable(section);
                } else if (amountOfInput == 5) {
                    Model model = new Model(strings[2].trim(), strings[3].trim(), strings[4].trim());
                    action.actionMake(section, commandForAction, model);
                } else {
                    action.help();
                    logger.warn("Wrong set of key phrases", str);
                    return false;
                }
            } else {
                System.out.println("Invalid input pattern.");
                action.help();
                logger.warn("Wrong command selected", str);
                return false;
            }
        } else {
            System.out.println("This table is not exist");
            logger.warn("Wrong section selected", str);
            action.help();
            return false;
        }
        return true;
    }

}
