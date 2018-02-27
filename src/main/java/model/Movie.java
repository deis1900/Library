package model;

public class Movie extends Model{
    public Movie(Long id, String name, String date, String author) {
        super(id, name, date, author);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + name + '\'' +
                ", release date='" + date + '\'' +
                ", producer='" + author + '\'' +
                '}';
    }
}
