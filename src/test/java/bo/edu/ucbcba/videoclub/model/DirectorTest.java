package bo.edu.ucbcba.videoclub.model;

import bo.edu.ucbcba.videoclub.exceptions.ValidationException;
import bo.edu.ucbcba.videoclub.model.Director;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DirectorTest {
    private Director director;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        director = new Director();
    }

    @Test
    public void TestId() {

        director.setId(25);
        assertEquals(25, director.getId());
    }


    @Test
    public void TestLastName(){
        director.setLastName("logos");
        assertEquals("logos",director.getLastName());
    }

    @Test
    public void TestFirstName(){
        director.setFirstName("Pedro");
        assertEquals("Pedro",director.getFirstName());
    }

    @Test
    public void TestMovies(){

        List<Movie> movies= new LinkedList<Movie>();
        director.setMovies(movies);
        assertEquals(movies,director.getMovies());
    }





}