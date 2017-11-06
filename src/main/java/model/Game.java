package model;

public class Game {

    private int id;
    private String gameName;
    private String year;

    public Game(int id, String gameName, String year) {

        this.id = id;
        this.gameName = gameName;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
