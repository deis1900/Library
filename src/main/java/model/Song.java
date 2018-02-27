package model;

public class Song extends Model{
    public Song(Long id, String name, String date, String author) {
        super(id, name, date, author);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release date='" + date + '\'' +
                ", artist='" + author + '\'' +
                '}';
    }
}
