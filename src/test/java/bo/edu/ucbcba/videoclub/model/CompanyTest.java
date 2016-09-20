package bo.edu.ucbcba.videoclub.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import bo.edu.ucbcba.videoclub.exceptions.ValidationException;
import bo.edu.ucbcba.videoclub.model.Company;

import java.util.LinkedList;
import java.util.List;

public class CompanyTest {
    private Company company;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        company = new Company();
    }

    @Test
    public void TestCountry() {

        company.setCountry("canada");
        assertEquals("canada",company.getCountry());
    }

    @Test
    public void TestId(){
        company.setId(25);
        assertEquals(25,company.getId());
    }

    @Test
    public void TestName(){
       company.setName("blizzard");
        assertEquals("blizzard",company.getName());
    }

    @Test
    public void TestGames(){
        List<Game> games= new LinkedList<Game>();
        company.setGames(games);
        assertEquals(games,company.getGames());
    }

}