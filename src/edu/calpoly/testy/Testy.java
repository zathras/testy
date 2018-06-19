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

import java.util.Collection;

/**
 * The main entry point for Testy.  To run a series of tests,
 * you pass an array of Test instances.  Lambda expressions
 * can be handy for this.  Assume, for example, you have a
 * test1() through test4() defined as instance methods of a class
 * called <code>MyTests</code>.  To run them, do:  
 * <pre>
 *     final MyTest testObj = new MyTest();
 *     Testy.run(
 *          () ->  testObj.test1(),
 *          () ->  testObj.test2(),
 *          () ->  testObj.test3(),
 *          () ->  testObj.test4()
 *     );
 * </pre>
 *
 */
public final class Testy {
    private Testy() {
    }

    /**
     * Run the given tests, and report which ones fail.
     * A test fails by throwing an exception.
     *
     * @return the number of failed tests.
     */
    public static int run (TestRunnable... tests) {
	int failed = 0;
	int passed = 0;
	for (int i = 0; i < tests.length; i++) {
	    try {
		tests[i].run();
		passed++;
	    } catch (Throwable t) {
		System.err.println("Test failed:");
		t.printStackTrace();
		System.err.println();
		failed++;
	    }
	}
	System.out.println("" + tests.length + " total tests:");
	System.out.println("    " + failed + " failed.");
	System.out.println("    " + passed + " passed.");
	return failed;
    }

    /**
     * Run the given tests, and report which ones fail.
     * This alternate entry point is given for those who prefer
     * lists.
     * A test fails by throwing an exception.
     *
     * @return the number of failed tests.
     */
    public static int run (Collection<TestRunnable> tests) {
	return run(tests.toArray(new TestRunnable[tests.size()]));
    }
}

