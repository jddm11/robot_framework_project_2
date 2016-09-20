package bo.edu.ucbcba.videoclub.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by USER on 28/05/2016.
 */
@Entity
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    @Column(length = 100)
    private String firstName;
    @Column(length = 100)
    private String lastName;
    @OneToMany  // uno para muchos
    private List<Movie> movies;

    public Director() {
      id=0;
        firstName="";
        lastName ="";
       movies= new LinkedList<Movie>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }


    @Override
    public String toString() {
        return String.format("%s %s" ,firstName , lastName);
    }
}
