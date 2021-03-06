package Input;

public class Model {

    private Long id;
    private String name;
    private String date;
    private String author;

    public Model(String name, String date, String author) {
        this.name = name;
        this.date = date;
        this.author = author;
    }

    public Model(Long id, String name, String date, String author) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String autor) {
        this.author = autor;
    }

    @Override
    public String toString() {
        return "Row{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
