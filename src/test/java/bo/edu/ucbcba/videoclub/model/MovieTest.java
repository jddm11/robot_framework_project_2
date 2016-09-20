package bo.edu.ucbcba.videoclub.model;

import bo.edu.ucbcba.videoclub.exceptions.ValidationException;
import bo.edu.ucbcba.videoclub.model.Movie;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class MovieTest {
    private Movie movie;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        movie = new Movie();
    }

    @Test
    public void TestTittle() {

        movie.setTitle("pinocho");
        assertEquals("pinocho",movie.getTitle());
    }

    @Test
    public void TestDescription(){
        movie.setDescription("hola como estas");
        assertEquals("hola como estas",movie.getDescription());
    }

    @Test
    public void TestPrice(){
        movie.setPrice(20);
        assertEquals(20,movie.getPrice());
    }

    @Test
    public void TestLegth(){
        movie.setLength(10);
        assertEquals(10,movie.getLength());
    }
    @Test
    public void TestRating(){
        movie.setRating(5);
        assertEquals(5,movie.getRating());
    }
    @Test
    public void TestId()
    {
        movie.setId(25);
        assertEquals(25,movie.getId());
    }
    @Test
    public void TestYear()
    {
        movie.setReleaseYear(2005);
        assertEquals(2005,movie.getReleaseYear());
    }

    @Test
    public void TestNameImage()
    {
        movie.setNameImage("C:/image/image.jpg");
        assertEquals("C:/image/image.jpg",movie.getNameImage());
    }

    @Test
    public void TestDirector()
    {
        Director director = new Director();
        movie.setDirector(director);
        assertEquals(director,movie.getDirector());
    }


}