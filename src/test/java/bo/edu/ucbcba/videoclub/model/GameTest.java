package bo.edu.ucbcba.videoclub.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class GameTest {
    private Game game;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void TestTitle()
    {
        game.setTitle("dota");
        assertEquals("dota",game.getTitle());
    }
    @Test
    public void TestDescription()
    {
        game.setDescription("dota");
        assertEquals("dota",game.getDescription());
    }
    @Test
    public void TestPrice()
    {
        game.setPrice(2);
        assertEquals(2,game.getPrice());
    }
    @Test
    public void TestId()
    {
        game.setId(3);
        assertEquals(3,game.getId());
    }
    @Test
    public void TestRating()
    {
        game.setRating(3);
        assertEquals(3,game.getRating());
    }
    @Test
    public void TestReleaseYear()
    {
        game.setReleaseYear(2001);
        assertEquals(2001,game.getReleaseYear());
    }
    @Test
    public void TestCompany()
    {
        Company company = new Company();
        game.setCompany(company);
        assertEquals(company,game.getCompany());
    }


}