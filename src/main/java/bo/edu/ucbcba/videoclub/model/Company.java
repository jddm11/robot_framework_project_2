package bo.edu.ucbcba.videoclub.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Samuel on 29/05/2016.
 */
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String country;
    @OneToMany
    private List<Game> games;

    public Company(){
        id = 0;
        name = "";
        country = "";
        games = new LinkedList<Game>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString(){return String.format("%s %s", name, country);  }
}
