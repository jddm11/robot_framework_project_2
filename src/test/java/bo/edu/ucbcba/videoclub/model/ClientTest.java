package bo.edu.ucbcba.videoclub.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import bo.edu.ucbcba.videoclub.exceptions.ValidationException;
import bo.edu.ucbcba.videoclub.model.Client;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    private Client client;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        client = new Client();
    }

    @Test
    public void TestFirstName() {

        client.setFirstname("Daniel");
        assertEquals("Daniel",client.getFirstname());

    }

    @Test
    public void TestLastName(){
        client.setLastname("Illanes");
        assertEquals("Illanes",client.getLastname());
    }

    @Test
    public void TestAddress(){
        client.setAddress("America #400");
        assertEquals("America #400",client.getAddress());

    }

    @Test
    public void TestCi(){
        client.setCi("123456");
        assertEquals("123456",client.getCi());
    }


}