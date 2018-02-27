package model;

public class Book extends Model {

    public Book(Long id, String name, String date, String author) {
        super(id, name, date, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + name + '\'' +
                ", release date='" + date + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
