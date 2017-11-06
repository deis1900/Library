package model;

public class Movie {

    private int id;
    private String movieName;
    private String year;

    public Movie(int id, String movieName, String year) {
        this.id = id;
        this.movieName = movieName;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
