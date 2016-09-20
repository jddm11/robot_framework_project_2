package bo.edu.ucbcba.videoclub.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private User user;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void TestPassword() {

       user.setPassword("123456");
        assertEquals("123456",user.getPassword());

    }

    @Test
    public void TestName(){
        user.setUsername("admin");
        assertEquals("admin",user.getUsername());
    }

    @Test
    public void TestUserType(){
        user.setUsertype(1);
        assertEquals(1,user.getUsertype());

    }

}