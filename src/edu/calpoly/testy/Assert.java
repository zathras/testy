/*
 * Copyright © 2018, Bill Foote, Cal Poly, San Luis Obispo, CA
 * 
 * Permission is hereby granted, free of charge, to any person obtaining 
 * a copy of this software and associated documentation files (the “Software”), 
 * to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, 
 * and/or sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS 
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package edu.calpoly.testy;

import java.util.Objects;
import java.util.Arrays;

/**
 * Various static methods to provide different test assertions.
 * This is modeled on org.junit.Assert; 
 * see https://junit.org/junit4/javadoc/latest/org/junit/Assert.html .
 * Use these assertions within a test.  If the assertion fails, a
 * runtime exception is thrown; that's how a test failure is indicated.
 * <p>
 * You might wish to use static imports, viz:
 * <pre>
 *   import static edu.calpoly.testy.Assert.assertTrue;
 *   import static edu.calpoly.testy.Assert.assertFalse;
 *   import static edu.calpoly.testy.Assert.fail;
 *   import static edu.calpoly.testy.Assert.assertEquals;
 *   import static edu.calpoly.testy.Assert.assertNotEquals;
 *   import static edu.calpoly.testy.Assert.assertSame;
 *   import static edu.calpoly.testy.Assert.assertNotSame;
 *   import static edu.calpoly.testy.Assert.assertNull;
 *   import static edu.calpoly.testy.Assert.assertNotNull;
 * </pre>
 */


public class Assert {

    private Assert() {
    }

    /**
     * Asserts that a condition is true.
     *
     * @param  message describing the test
     * @param  condition condition to be checked
     * @throws TestFailure   if the condition is false
     */
    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            fail(message);
        }
    }

    /**
     * Asserts that a condition is false.
     *
     * @param  message describing the test
     * @param  condition condition to be checked
     * @throws TestFailure   if the condition isn't false
     */
    public static void assertFalse(String message, boolean condition) {
        if (condition) {
            fail(message);
        }
    }

    /**
     * Fails a test with the given message.
     *
     * @param message describing the failure
     * @throws TestFailed
     */
    public static void fail(String message) {
        if (message == null) {
            throw new TestFailed();
        }
        throw new TestFailed(message);
    }

    /**
     * Asserts that two objects are equal, as determined by
     * <code>java.util.Objects.deepEquals()</code>.  Note that
     * if the objects aren't arrays, this is the same as
     * <code>java.util.Objects.equals()</code>.  This method works
     * for primitive values, like int or long, via autoboxing.  It
     * works for arrays, including primitive arrays and multi-dimensional
     * arrays.
     *
     * @param  message describing the test
     * @param  expected expected value
     * @param  actual actual value
     * @throws TestFailed if they aren't equal
     */
    public static void assertEquals(String message, Object expected,
				    Object actual) 
    {
        if (Objects.deepEquals(expected, actual)) {
            return;
        }
	message = "" + message + " : "
		  + "\nexpected:  " + toString(expected)
		  + "\nactual:  " + toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Asserts that two objects are not equal, as determined by
     * <code>java.util.Objects.deepEquals()</code>.  Note that
     * if the objects aren't arrays, this is the same as
     * <code>java.util.Objects.equals()</code>.  This method works
     * for primitive values, like int or long, via autoboxing.  It
     * works for arrays, including primitive arrays and multi-dimensional
     * arrays.
     *
     * @param  message describing the test
     * @param  unexpected unexpected value
     * @param  actual actual value
     * @throws TestFailed if they aren't equal
     */
    public static void assertNotEquals(String message, Object unexpected, Object actual) {
        if (!Objects.deepEquals(expected, actual)) {
            return;
        }
	message = "" + message + " : "
		  + "\nunexpected:  " + toString(unexpected)
		  + "\nactual:  " + toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if expected == actual.
     *
     * @param  message describing the test
     * @param  expected expected value
     * @param  actual actual value
     * @throws TestFailed if they aren't ==
     */
    public static void assertSame(String message, Object expected, 
    				  Object actual)
    {
	if (expected != actual) {
	    message = "" + message + " : "
		      + "\nexpected:  " + toString(expected)
		      + "\nactual:  " + toString(actual);
	    throw new TestFailed(message);
	}
    }

    /**
     * Determine if expected != actual.
     *
     * @param unexpected unexpected value
     * @param actual actual value
     * @throws TestFailed if they are ==
     */
    public static void assertNotSame(String message, Object unexpected, 
    				     Object actual)
    {
	if (expected == actual) {
	    message = "" + message + " : "
		      + "\nunexpected:  " + toString(expected)
		      + "\nactual:  " + toString(actual);
	    throw new TestFailed(message);
	}
    }

    /**
     * Determine if object == null.
     *
     * @param  message describing the test
     * @param  object  The object to test
     * @throws TestFailed if it is != null
     */
    public static void assertNull(String message, Object object) {
	if (object != null) {
	    if (message == null) {
		throw new TestFailed();
	    } else {
		message = message + " : expected null, got " + toString(object);
		throw new TestFailed(message);
	    }
	}
    }

    /**
     * Determine if object != null.
     *
     * @param  message describing the test
     * @param  object  The object to test
     * @throws TestFailed if it is == null
     */
    public static void assertNotNull(String message, Object object) {
	if (object == null) {
	    message = "" + message + " : expected non-null, got null";
	    throw new TestFailed(message);
	}
    }

    /**
     * Determine if two boolean values are equal.
     * This method is only overloaded because it's slightly
     * more efficient if we avoid autoboxing.
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, boolean expected, boolean actual) {
	if (expected != actual) {
	    message = "" + message + " : expected " + expected + ", got " + actual;
	    throw new TestFailed(message);
	}
    }

    /**
     * Determine if two boolean values are not equal.
     * This method is only overloaded because it's slightly
     * more efficient if we avoid autoboxing.
     *
     * @param  message describing the test
     * @param  unexpected The unexpected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(String message, boolean unexpected, boolean actual) {
	if (unexpected == actual) {
	    message = "" + message + " : expected different, both were " + expected;
	    throw new TestFailed(message);
	}
    }

    /**
     * Determine if two byte values are equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, byte expected, byte actual) {
	if (expected != actual) {
	    message = "" + message + " : expected " + expected + ", got " + actual;
	    throw new TestFailed(message);
	}
    }

    /**
     * Determine if two byte values are not equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  message describing the test
     * @param  unexpected The unexpected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(String message, byte unexpected, byte actual) {
	if (unexpected == actual) {
	    message = "" + message + " : expected different, both were " + expected;
	    throw new TestFailed(message);
	}
    }

    /**
     * Determine if two char values are equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, char expected, char actual) {
	if (expected != actual) {
	    message = "" + message + " : expected " + expected + ", got " + actual;
	    throw new TestFailed(message);
	}
    }

    /**
     * Determine if two char values are not equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  message describing the test
     * @param  unexpected The unexpected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(String message, char unexpected, char actual) {
	if (unexpected == actual) {
	    message = "" + message + " : expected different, both were " + expected;
	    throw new TestFailed(message);
	}
    }


    /**
     * Determine if two int values are equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, int expected, int actual) {
	if (expected != actual) {
	    message = "" + message + " : expected " + expected + ", got " + actual;
	    throw new TestFailed(message);
	}
    }

    /**
     * Determine if two int values are not equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  message describing the test
     * @param  unexpected The unexpected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(String message, int unexpected, int actual) {
	if (unexpected == actual) {
	    message = "" + message + " : expected different, both were " + expected;
	    throw new TestFailed(message);
	}
    }


    /**
     * Determine if two long values are equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, long expected, long actual) {
	if (expected != actual) {
	    message = "" + message + " : expected " + expected + ", got " + actual;
	    throw new TestFailed(message);
	}
    }

    /**
     * Determine if two long values are not equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  message describing the test
     * @param  unexpected The unexpected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(String message, long unexpected, long actual) {
	if (unexpected == actual) {
	    message = "" + message + " : expected different, both were " + expected;
	    throw new TestFailed(message);
	}
    }

    /**
     * Determine if two short values are equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, short expected, short actual) {
	if (expected != actual) {
	    message = "" + message + " : expected " + expected + ", got " + actual;
	    throw new TestFailed(message);
	}
    }

    /**
     * Determine if two short values are not equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  message describing the test
     * @param  unexpected The unexpected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(String message, short unexpected, short actual) {
	if (unexpected == actual) {
	    message = "" + message + " : expected different, both were " + expected;
	    throw new TestFailed(message);
	}
    }

    /**
     * Determine if two float values are equal within a small tolerance.
     * They are equal if they are the same as determined by
     * <code>java.lang.Float.compare()</code> or if the absolute value
     * of their difference is less than or equal to epsilon.
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, float expected, float actual, float epsilon) 
    {
	if (Float.compare(expected, actual) == 0) {
	    return;
	}
	if (Math.abs(expected - actual) <= epsilon) {
	    return;
	}
	message = "" + message + " : expected " + expected + ", got " + actual;
	throw new TestFailed(message);
    }


    /**
     * Determine if two float values are not equal within a small tolerance.
     * They are equal if they are the same as determined by
     * <code>java.lang.Float.compare()</code> or if the absolute value
     * of their difference is less than or equal to epsilon.
     *
     * @param  message describing the test
     * @param  unexpected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(String message, float unexpected, float actual, float epsilon) 
    {
	if (Float.compare(expected, actual) != 0) {
	    if (Math.abs(expected - actual) > epsilon) {
		return;
	    }
	}
	message = "" + message + " : expected " + expected + ", got " + actual;
	throw new TestFailed(message);
    }

    /**
     * Determine if two float arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, float[] expected, float[] actual, float epsilon) 
    {
	if (expected == actual) {
	    return;
	}
	if (expected != null && actual != null && expected.length == actual.length) {
	    try {
		for (int i = 0; i < expected.length; i++) {
		    assertEquals(message, expected[i], actual[i]);
		}
		return;
	    } catch (TestFailed failed) {
		// Generate a new exception below
	    }
	}
	message = "" + message + " : expected " + Arrays.toString(expected) 
	          + ", got " + Arrays.toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two float arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, float[][] expected, float[][] actual, float epsilon) 
    {
	if (expected == actual) {
	    return;
	}
	if (expected != null && actual != null && expected.length == actual.length) {
	    try {
		for (int i = 0; i < expected.length; i++) {
		    assertEquals(message, expected[i], actual[i]);
		}
		return;
	    } catch (TestFailed failed) {
		// Generate a new exception below
	    }
	}
	message = "" + message + " : expected " + Arrays.toString(expected) 
	          + ", got " + Arrays.toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two float arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, float[][][] expected, float[][][] actual, float epsilon) 
    {
	if (expected == actual) {
	    return;
	}
	if (expected != null && actual != null && expected.length == actual.length) {
	    try {
		for (int i = 0; i < expected.length; i++) {
		    assertEquals(message, expected[i], actual[i]);
		}
		return;
	    } catch (TestFailed failed) {
		// Generate a new exception below
	    }
	}
	message = "" + message + " : expected " + Arrays.toString(expected) 
	          + ", got " + Arrays.toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two float arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, float[][][][] expected, float[][][][] actual, float epsilon) 
    {
	if (expected == actual) {
	    return;
	}
	if (expected != null && actual != null && expected.length == actual.length) {
	    try {
		for (int i = 0; i < expected.length; i++) {
		    assertEquals(message, expected[i], actual[i]);
		}
		return;
	    } catch (TestFailed failed) {
		// Generate a new exception below
	    }
	}
	message = "" + message + " : expected " + Arrays.toString(expected) 
	          + ", got " + Arrays.toString(actual);
	throw new TestFailed(message);
    }


    /**
     * Determine if two double values are equal within a small tolerance.
     * They are equal if they are the same as determined by
     * <code>java.lang.Double.compare()</code> or if the absolute value
     * of their difference is less than or equal to epsilon.
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, double expected, double actual, double epsilon) 
    {
	if (Double.compare(expected, actual) == 0) {
	    return;
	}
	if (Math.abs(expected - actual) <= epsilon) {
	    return;
	}
	message = "" + message + " : expected " + expected + ", got " + actual;
	throw new TestFailed(message);
    }


    /**
     * Determine if two double values are not equal within a small tolerance.
     * They are equal if they are the same as determined by
     * <code>java.lang.Double.compare()</code> or if the absolute value
     * of their difference is less than or equal to epsilon.
     *
     * @param  message describing the test
     * @param  unexpected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(String message, double unexpected, double actual, double epsilon) 
    {
	if (Double.compare(expected, actual) != 0) {
	    if (Math.abs(expected - actual) > epsilon) {
		return;
	    }
	}
	message = "" + message + " : expected " + expected + ", got " + actual;
	throw new TestFailed(message);
    }

    /**
     * Determine if two double arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, double[] expected, double[] actual, double epsilon) 
    {
	if (expected == actual) {
	    return;
	}
	if (expected != null && actual != null && expected.length == actual.length) {
	    try {
		for (int i = 0; i < expected.length; i++) {
		    assertEquals(message, expected[i], actual[i]);
		}
		return;
	    } catch (TestFailed failed) {
		// Generate a new exception below
	    }
	}
	message = "" + message + " : expected " + Arrays.toString(expected) 
	          + ", got " + Arrays.toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two double arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, double[][] expected, double[][] actual, double epsilon) 
    {
	if (expected == actual) {
	    return;
	}
	if (expected != null && actual != null && expected.length == actual.length) {
	    try {
		for (int i = 0; i < expected.length; i++) {
		    assertEquals(message, expected[i], actual[i]);
		}
		return;
	    } catch (TestFailed failed) {
		// Generate a new exception below
	    }
	}
	message = "" + message + " : expected " + Arrays.toString(expected) 
	          + ", got " + Arrays.toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two double arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, double[][][] expected, double[][][] actual, double epsilon) 
    {
	if (expected == actual) {
	    return;
	}
	if (expected != null && actual != null && expected.length == actual.length) {
	    try {
		for (int i = 0; i < expected.length; i++) {
		    assertEquals(message, expected[i], actual[i]);
		}
		return;
	    } catch (TestFailed failed) {
		// Generate a new exception below
	    }
	}
	message = "" + message + " : expected " + Arrays.toString(expected) 
	          + ", got " + Arrays.toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two double arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  message describing the test
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(String message, double[][][][] expected, double[][][][] actual, 
    	         double epsilon) 
    {
	if (expected == actual) {
	    return;
	}
	if (expected != null && actual != null && expected.length == actual.length) {
	    try {
		for (int i = 0; i < expected.length; i++) {
		    assertEquals(message, expected[i], actual[i]);
		}
		return;
	    } catch (TestFailed failed) {
		// Generate a new exception below
	    }
	}
	message = "" + message + " : expected " + Arrays.toString(expected) 
	          + ", got " + Arrays.toString(actual);
	throw new TestFailed(message);
    }


    private String toString(Object o) {
	if (o instanceof Object[]) {
	    return Arrays.deepToString((Object[]) o);
	} else {
	    return Objects.toString(o);
	}
    }
}
