package model;

public class Song {

    private int id;
    private String songName;
    private String year;

    public Song(int id, String songName, String year) {
        this.id = id;
        this.songName = songName;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
