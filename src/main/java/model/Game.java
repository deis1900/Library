package model;

public class Game extends Model{

    public Game(Long id, String name, String date, String author) {
        super(id, name, date, author);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release date='" + date + '\'' +
                ", video game publisher='" + author + '\'' +
                '}';
    }
}
