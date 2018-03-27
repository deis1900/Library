package Input;

public class Validator {

//    Verify validity of incoming data

    public Boolean checkForHelpCommand (String string) {
        return string.toUpperCase().equals("HELP")
                || string.equals("");
    }
    public Boolean checkForQuitCommand (String string) {
        return string.toUpperCase().equals("QUIT")
                || string.toUpperCase().equals("EXIT");
    }
    public Boolean validNameOfTable(String tableName) {
        return tableName.equals("BOOK")
                || tableName.equals("SONG")
                || tableName.equals("MOVIE")
                || tableName.equals("GAME");
    }
    public Boolean validCommand(String string) {
        return string.equals("ADD")
                || string.equals("REMOVE")
                || string.equals("UPDATE")
                || string.equals("LIST");
    }

    public String validateInput(String string) {
        return string.replaceAll("`", "''").replaceAll("'", "''");
    }

}
