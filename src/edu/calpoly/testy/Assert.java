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
		  + "\nactual:    " + toString(actual);
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
        if (!Objects.deepEquals(unexpected, actual)) {
            return;
        }
	message = "" + message + " : "
		  + "\nunexpected:  " + toString(unexpected)
		  + "\nactual:      " + toString(actual);
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
		      + "\nactual:    " + toString(actual);
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
	if (unexpected == actual) {
	    message = "" + message + " : "
		      + "\nunexpected:  " + toString(unexpected)
		      + "\nactual:      " + toString(actual);
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
	    message = "" + message + " : expected different, both were " + actual;
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
	    message = "" + message + " : expected different, both were " + actual;
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
	    message = "" + message + " : expected different, both were " + actual;
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
	    message = "" + message + " : expected different, both were " + actual;
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
	    message = "" + message + " : expected different, both were " + actual;
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
	    message = "" + message + " : expected different, both were " + actual;
	    throw new TestFailed(message);
	}
    }


    private static boolean doublesSame(double a, double b, double epsilon) {
	if (Double.compare(a, b) == 0) {
	    return true;
	} else {
	    return Math.abs(a - b) <= epsilon;
	}
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
	if (doublesSame(expected, actual, epsilon)) {
	    return;
	}
	message = "" + message
		  + " : expected:  " + expected 
		  + "  actual:  " + actual;
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
	if (!doublesSame(unexpected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + ": unexpected:  " + unexpected 
		  + " actual:  " + actual;
	throw new TestFailed(message);
    }

    private static boolean doublesSame(double[] a, double[] b, double epsilon) {
	if (a == b) {
	    return true;
	} else if (a == null || b == null) {
	    return false;
	} else if (a.length != b.length) {
	    return false;
	} else {
	    for (int i = 0; i < a.length; i++) {
		if (!doublesSame(a[i], b[i], epsilon)) {
		    return false;
		}
	    }
	    return true;
	}
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
	if (doublesSame(expected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nexpected:  " + toString(expected) 
		  + "\nactual:    " + toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two double arrays are not equal, with the values checked within a
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
    assertNotEquals(String message, double[] unexpected, double[] actual, double epsilon) 
    {
	if (!doublesSame(unexpected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nunexpected:  " + toString(unexpected) 
		  + "\nactual:      " + toString(actual);
	throw new TestFailed(message);
    }


    private static boolean doublesSame(double[][] a, double[][] b, double epsilon) {
	if (a == b) {
	    return true;
	} else if (a == null || b == null) {
	    return false;
	} else if (a.length != b.length) {
	    return false;
	} else {
	    for (int i = 0; i < a.length; i++) {
		if (!doublesSame(a[i], b[i], epsilon)) {
		    return false;
		}
	    }
	    return true;
	}
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
	if (doublesSame(expected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nexpected:  " + toString(expected) 
		  + "\nactual:    " + toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two double arrays are not equal, with the values checked within a
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
    assertNotEquals(String message, double[][] unexpected, double[][] actual, double epsilon) 
    {
	if (!doublesSame(unexpected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nunexpected:  " + toString(unexpected) 
		  + "\nactual:      " + toString(actual);
	throw new TestFailed(message);
    }

    private static boolean doublesSame(double[][][] a, double[][][] b, double epsilon) {
	if (a == b) {
	    return true;
	} else if (a == null || b == null) {
	    return false;
	} else if (a.length != b.length) {
	    return false;
	} else {
	    for (int i = 0; i < a.length; i++) {
		if (!doublesSame(a[i], b[i], epsilon)) {
		    return false;
		}
	    }
	    return true;
	}
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
	if (doublesSame(expected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nexpected:  " + toString(expected) 
		  + "\nactual:    " + toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two double arrays are not equal, with the values checked within a
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
    assertNotEquals(String message, double[][][] unexpected, double[][][] actual, 
    		    double epsilon) 
    {
	if (!doublesSame(unexpected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nunexpected:  " + toString(unexpected) 
		  + "\nactual:      " + toString(actual);
	throw new TestFailed(message);
    }

    private static boolean doublesSame(double[][][][] a, double[][][][] b, double epsilon) {
	if (a == b) {
	    return true;
	} else if (a == null || b == null) {
	    return false;
	} else if (a.length != b.length) {
	    return false;
	} else {
	    for (int i = 0; i < a.length; i++) {
		if (!doublesSame(a[i], b[i], epsilon)) {
		    return false;
		}
	    }
	    return true;
	}
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
	if (doublesSame(expected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nexpected:  " + toString(expected) 
		  + "\nactual:    " + toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two double arrays are not equal, with the values checked within a
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
    assertNotEquals(String message, double[][][][] unexpected, double[][][][] actual, 
    		    double epsilon) 
    {
	if (!doublesSame(unexpected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nunexpected:  " + toString(unexpected) 
		  + "\nactual:      " + toString(actual);
	throw new TestFailed(message);
    }


    private static boolean floatsSame(float a, float b, float epsilon) {
	if (Float.compare(a, b) == 0) {
	    return true;
	} else {
	    return Math.abs(a - b) <= epsilon;
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
	if (floatsSame(expected, actual, epsilon)) {
	    return;
	}
	message = "" + message
		  + " : expected:  " + expected 
		  + "  actual:  " + actual;
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
	if (!floatsSame(unexpected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + ": unexpected:  " + unexpected 
		  + " actual:  " + actual;
	throw new TestFailed(message);
    }

    private static boolean floatsSame(float[] a, float[] b, float epsilon) {
	if (a == b) {
	    return true;
	} else if (a == null || b == null) {
	    return false;
	} else if (a.length != b.length) {
	    return false;
	} else {
	    for (int i = 0; i < a.length; i++) {
		if (!floatsSame(a[i], b[i], epsilon)) {
		    return false;
		}
	    }
	    return true;
	}
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
	if (floatsSame(expected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nexpected:  " + toString(expected) 
		  + "\nactual:    " + toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two float arrays are not equal, with the values checked within a
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
    assertNotEquals(String message, float[] unexpected, float[] actual, float epsilon) 
    {
	if (!floatsSame(unexpected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nunexpected:  " + toString(unexpected) 
		  + "\nactual:      " + toString(actual);
	throw new TestFailed(message);
    }


    private static boolean floatsSame(float[][] a, float[][] b, float epsilon) {
	if (a == b) {
	    return true;
	} else if (a == null || b == null) {
	    return false;
	} else if (a.length != b.length) {
	    return false;
	} else {
	    for (int i = 0; i < a.length; i++) {
		if (!floatsSame(a[i], b[i], epsilon)) {
		    return false;
		}
	    }
	    return true;
	}
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
	if (floatsSame(expected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nexpected:  " + toString(expected) 
		  + "\nactual:    " + toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two float arrays are not equal, with the values checked within a
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
    assertNotEquals(String message, float[][] unexpected, float[][] actual, float epsilon) 
    {
	if (!floatsSame(unexpected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nunexpected:  " + toString(unexpected) 
		  + "\nactual:      " + toString(actual);
	throw new TestFailed(message);
    }

    private static boolean floatsSame(float[][][] a, float[][][] b, float epsilon) {
	if (a == b) {
	    return true;
	} else if (a == null || b == null) {
	    return false;
	} else if (a.length != b.length) {
	    return false;
	} else {
	    for (int i = 0; i < a.length; i++) {
		if (!floatsSame(a[i], b[i], epsilon)) {
		    return false;
		}
	    }
	    return true;
	}
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
	if (floatsSame(expected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nexpected:  " + toString(expected) 
		  + "\nactual:    " + toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two float arrays are not equal, with the values checked within a
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
    assertNotEquals(String message, float[][][] unexpected, float[][][] actual, 
    		    float epsilon) 
    {
	if (!floatsSame(unexpected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nunexpected:  " + toString(unexpected) 
		  + "\nactual:      " + toString(actual);
	throw new TestFailed(message);
    }

    private static boolean floatsSame(float[][][][] a, float[][][][] b, float epsilon) {
	if (a == b) {
	    return true;
	} else if (a == null || b == null) {
	    return false;
	} else if (a.length != b.length) {
	    return false;
	} else {
	    for (int i = 0; i < a.length; i++) {
		if (!floatsSame(a[i], b[i], epsilon)) {
		    return false;
		}
	    }
	    return true;
	}
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
    assertEquals(String message, float[][][][] expected, float[][][][] actual, 
    		 float epsilon) 
    {
	if (floatsSame(expected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nexpected:  " + toString(expected) 
		  + "\nactual:    " + toString(actual);
	throw new TestFailed(message);
    }

    /**
     * Determine if two float arrays are not equal, with the values checked within a
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
    assertNotEquals(String message, float[][][][] unexpected, float[][][][] actual, 
    		    float epsilon) 
    {
	if (!floatsSame(unexpected, actual, epsilon)) {
	    return;
	}
	message = "" + message 
		  + "\nunexpected:  " + toString(unexpected) 
		  + "\nactual:      " + toString(actual);
	throw new TestFailed(message);
    }

    /*********************************************************
     *           Next, the same thing with no messages       *
     *********************************************************/


    /**
     * Asserts that a condition is true.
     *
     * @param  condition condition to be checked
     * @throws TestFailure   if the condition is false
     */
    public static void assertTrue(boolean condition) {
	assertTrue("", condition);
    }

    /**
     * Asserts that a condition is false.
     *
     * @param  condition condition to be checked
     * @throws TestFailure   if the condition isn't false
     */
    public static void assertFalse(boolean condition) {
	assertFalse("", condition);
    }

    /**
     * Fails a test with no message.
     *
     * @throws TestFailed
     */
    public static void fail() {
	fail("");
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
     * @param  expected expected value
     * @param  actual actual value
     * @throws TestFailed if they aren't equal
     */
    public static void assertEquals(Object expected, Object actual) 
    {
	assertEquals("", expected, actual);
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
     * @param  unexpected unexpected value
     * @param  actual actual value
     * @throws TestFailed if they aren't equal
     */
    public static void assertNotEquals(Object unexpected, Object actual) 
    {
	assertNotEquals("", unexpected, actual);
    }

    /**
     * Determine if expected == actual.
     *
     * @param  expected expected value
     * @param  actual actual value
     * @throws TestFailed if they aren't ==
     */
    public static void assertSame(Object expected, Object actual)
    {
	assertSame("", expected, actual);
    }

    /**
     * Determine if expected != actual.
     *
     * @param unexpected unexpected value
     * @param actual actual value
     * @throws TestFailed if they are ==
     */
    public static void assertNotSame(Object unexpected, Object actual)
    {
	assertNotSame("", unexpected, actual);
    }

    /**
     * Determine if object == null.
     *
     * @param  object  The object to test
     * @throws TestFailed if it is != null
     */
    public static void assertNull(Object object) {
	assertNull("", object);
    }

    /**
     * Determine if object != null.
     *
     * @param  object  The object to test
     * @throws TestFailed if it is == null
     */
    public static void assertNotNull(Object object) {
	assertNotNull("", object);
    }

    /**
     * Determine if two boolean values are equal.
     * This method is only overloaded because it's slightly
     * more efficient if we avoid autoboxing.
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(boolean expected, boolean actual) 
    {
	assertEquals("", expected, actual);
    }

    /**
     * Determine if two boolean values are not equal.
     * This method is only overloaded because it's slightly
     * more efficient if we avoid autoboxing.
     *
     * @param  unexpected The unexpected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(boolean unexpected, boolean actual) 
    {
	assertNotEquals("", unexpected, actual);
    }

    /**
     * Determine if two byte values are equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(byte expected, byte actual) 
    {
	assertEquals("", expected, actual);
    }

    /**
     * Determine if two byte values are not equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  unexpected The unexpected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(byte unexpected, byte actual) 
    {
	assertNotEquals("", unexpected, actual);
    }

    /**
     * Determine if two char values are equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(char expected, char actual) 
    {
	assertEquals("", expected, actual);
    }

    /**
     * Determine if two char values are not equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  unexpected The unexpected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(char unexpected, char actual) 
    {
	assertNotEquals("", unexpected, actual);
    }


    /**
     * Determine if two int values are equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(int expected, int actual) 
    {
	assertEquals("", expected, actual);
    }

    /**
     * Determine if two int values are not equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  unexpected The unexpected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(int unexpected, int actual) 
    {
	assertNotEquals("", unexpected, actual);
    }


    /**
     * Determine if two long values are equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(long expected, long actual) 
    {
	assertEquals("", expected, actual);
    }

    /**
     * Determine if two long values are not equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  unexpected The unexpected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(long unexpected, long actual) 
    {
	assertNotEquals("", unexpected, actual);
    }

    /**
     * Determine if two short values are equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(short expected, short actual) 
    {
	assertEquals("", expected, actual);
    }

    /**
     * Determine if two short values are not equal.  This method is only overloaded
     * because it's slightly more efficient if we avoid autoboxing.
     *
     * @param  unexpected The unexpected value
     * @param  actual   The actual value
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(short unexpected, short actual) 
    {
	assertNotEquals("", unexpected, actual);
    }


    /**
     * Determine if two double values are equal within a small tolerance.
     * They are equal if they are the same as determined by
     * <code>java.lang.Double.compare()</code> or if the absolute value
     * of their difference is less than or equal to epsilon.
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(double expected, double actual, double epsilon) 
    {
	assertEquals("", expected, actual, epsilon);
    }


    /**
     * Determine if two double values are not equal within a small tolerance.
     * They are equal if they are the same as determined by
     * <code>java.lang.Double.compare()</code> or if the absolute value
     * of their difference is less than or equal to epsilon.
     *
     * @param  unexpected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(double unexpected, double actual, double epsilon) 
    {
	assertNotEquals("", unexpected, actual, epsilon);
    }


    /**
     * Determine if two double arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(double[] expected, double[] actual, double epsilon) 
    {
	assertEquals("", expected, actual, epsilon);
    }

    /**
     * Determine if two double arrays are not equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(double[] unexpected, double[] actual, double epsilon) 
    {
	assertNotEquals("", unexpected, actual, epsilon);
    }


    /**
     * Determine if two double arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(double[][] expected, double[][] actual, double epsilon) 
    {
	assertEquals("", expected, actual, epsilon);
    }

    /**
     * Determine if two double arrays are not equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(double[][] unexpected, double[][] actual, double epsilon) 
    {
	assertNotEquals("", unexpected, actual, epsilon);
    }


    /**
     * Determine if two double arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(double[][][] expected, double[][][] actual, double epsilon) 
    {
	assertEquals("", expected, actual, epsilon);
    }

    /**
     * Determine if two double arrays are not equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(double[][][] unexpected, double[][][] actual, double epsilon) 
    {
	assertNotEquals("", unexpected, actual, epsilon);
    }


    /**
     * Determine if two double arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(double[][][][] expected, double[][][][] actual, double epsilon) 
    {
	assertEquals("", expected, actual, epsilon);
    }

    /**
     * Determine if two double arrays are not equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(double[][][][] unexpected, double[][][][] actual, double epsilon) 
    {
	assertNotEquals("", unexpected, actual, epsilon);
    }


    /**
     * Determine if two float values are equal within a small tolerance.
     * They are equal if they are the same as determined by
     * <code>java.lang.Float.compare()</code> or if the absolute value
     * of their difference is less than or equal to epsilon.
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(float expected, float actual, float epsilon) 
    {
	assertEquals("", expected, actual, epsilon);
    }


    /**
     * Determine if two float values are not equal within a small tolerance.
     * They are equal if they are the same as determined by
     * <code>java.lang.Float.compare()</code> or if the absolute value
     * of their difference is less than or equal to epsilon.
     *
     * @param  unexpected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(float unexpected, float actual, float epsilon) 
    {
	assertNotEquals("", unexpected, actual, epsilon);
    }

    /**
     * Determine if two float arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(float[] expected, float[] actual, float epsilon) 
    {
	assertEquals("", expected, actual, epsilon);
    }

    /**
     * Determine if two float arrays are not equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(float[] unexpected, float[] actual, float epsilon) 
    {
	assertNotEquals("", unexpected, actual, epsilon);
    }


    /**
     * Determine if two float arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(float[][] expected, float[][] actual, float epsilon) 
    {
	assertEquals("", expected, actual, epsilon);
    }

    /**
     * Determine if two float arrays are not equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(float[][] unexpected, float[][] actual, float epsilon) 
    {
	assertNotEquals("", unexpected, actual, epsilon);
    }

    /**
     * Determine if two float arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(float[][][] expected, float[][][] actual, float epsilon) 
    {
	assertEquals("", expected, actual, epsilon);
    }

    /**
     * Determine if two float arrays are not equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(float[][][] unexpected, float[][][] actual, float epsilon) 
    {
	assertNotEquals("", unexpected, actual, epsilon);
    }


    /**
     * Determine if two float arrays are equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertEquals(float[][][][] expected, float[][][][] actual, float epsilon) 
    {
	assertEquals("", expected, actual, epsilon);
    }

    /**
     * Determine if two float arrays are not equal, with the values checked within a
     * small tolerance
     *
     * @param  expected The expected value
     * @param  actual   The actual value
     * @param  epsilon  The tolerance
     *
     * @throws TestFailed  on test failure
     */
    public static void 
    assertNotEquals(float[][][][] unexpected, float[][][][] actual, float epsilon) 
    {
	assertNotEquals("", unexpected, actual, epsilon);
    }



    //
    // toString that will do something reasonable with primitive arrays,
    // including multi-dimensional primitve arrays.
    //
    private static String toString(Object o) {
	if (o instanceof Object[]) {
	    // This handles multi-dimensional primitive arrays,
	    // like int[][]
	    return Arrays.deepToString((Object[]) o);
	} else if (o instanceof boolean[]) {
	    return Arrays.toString((boolean[]) o);
	} else if (o instanceof byte[]) {
	    return Arrays.toString((byte[]) o);
	} else if (o instanceof char[]) {
	    return Arrays.toString((char[]) o);
	} else if (o instanceof double[]) {
	    return Arrays.toString((double[]) o);
	} else if (o instanceof float[]) {
	    return Arrays.toString((float[]) o);
	} else if (o instanceof int[]) {
	    return Arrays.toString((int[]) o);
	} else if (o instanceof long[]) {
	    return Arrays.toString((long[]) o);
	} else if (o instanceof short[]) {
	    return Arrays.toString((short[]) o);
	} else {
	    return Objects.toString(o);
	}
    }
}
