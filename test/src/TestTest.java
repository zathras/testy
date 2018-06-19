
import edu.calpoly.testy.Testy;
import static edu.calpoly.testy.Assert.assertTrue;
import static edu.calpoly.testy.Assert.assertFalse;
import static edu.calpoly.testy.Assert.fail;
import static edu.calpoly.testy.Assert.assertEquals;
import static edu.calpoly.testy.Assert.assertNotEquals;
import static edu.calpoly.testy.Assert.assertSame;
import static edu.calpoly.testy.Assert.assertNotSame;
import static edu.calpoly.testy.Assert.assertNull;
import static edu.calpoly.testy.Assert.assertNotNull;

/**
 * This is a little class put together to test the testing
 * framework.
 */

public class TestTest {

    @Test
    public void testPass1() {
	// Doing nothing passes
    }

    @Test
    public void testPass2() {
	assertTrue("Pass 2", true);
    }

    @Test
    public void testFail1() {
	assertEquals("Fail 1", 2, 3);
    }

    @Test
    public void testFail2() {
	assertEquals("Fail 2", 2.0, 3.0, 0.0001);
    }

    public static void main(String[] args) {
	Final TestTest tests = new TestTest();
	Testy.run(
	    () -> tests.testPass1(),
	    () -> tests.testPass2(),
	    () -> tests.testFail1(),
	    () -> tests.testFail2()
	);
    }
}

