package top.wiklservice_dgraph.controller;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {

    String uid;
    String name;
    String release_date;
    String revenue;
    String running_time;

    List<Person> director;
    List<Person> starring;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getRunning_time() {
        return running_time;
    }

    public void setRunning_time(String running_time) {
        this.running_time = running_time;
    }

    public List<Person> getDirector() {
        return director;
    }

    public void setDirector(List<Person> director) {
        this.director = director;
    }

    public List<Person> getStarring() {
        return starring;
    }

    public void setStarring(List<Person> starring) {
        this.starring = starring;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", release_date='" + release_date + '\'' +
                ", revenue='" + revenue + '\'' +
                ", running_time='" + running_time + '\'' +
                ", director=" + director +
                ", starring=" + starring +
                '}';
    }
}
