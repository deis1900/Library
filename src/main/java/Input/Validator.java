package Input;

public class Validator {

    public Boolean verifyNameOfTable(String tableName) {
        String unifiedValue = tableName.toUpperCase();
        if(unifiedValue.equals("BOOK")
                || unifiedValue.equals("SONG")
                || unifiedValue.equals("MOVIE")
                || unifiedValue.equals("GAME")){
            return true;
        } else if (unifiedValue.equals("QUIT")) {
            System.exit(0);
            return false;
        }
        else return false;
    }

    public Boolean filterCommandList(String commandForAction) {
        String unifiedValue = commandForAction.toUpperCase();
        if(unifiedValue.equals("LIST")){
            return false;
        }
        else return true;
    }
}
