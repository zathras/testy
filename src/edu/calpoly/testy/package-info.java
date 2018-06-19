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


/**
 * Testy - a simple unit test framework
 * <br>
 * <br>
 * <center>
 * <img src="doc-files/testy.png" alt="*">
 * </center>
 * <p>
 * This package provides a framework for unit testing, inspired by
 * <a href="https://junit.org/">JUnit</a>.  This package steers clear
 * of language idioms that are unsuitable for an introductory OO class,
 * like the Java reflaction API.  It just provides a way of gathering
 * unit tests together, and a set of convenience functions for comparing
 * data values .
 * <p>
 * The main entry point for this package is the class 
 * <a href="Testy.html"><code>Testy</code></a>.  An application simply
 * calls {@link Testy#run(TestRunnable...) Testy.run()} with some 
 * number of unit tests.
 * <p>
 * See also 
 * <a href="https://testy.jovial.com/" target="_top">https://testy.jovial.com/</a>,
 * and <a href="doc-files/LICENSE.txt">the license</a>.
 *
 *
 *      @author         Bill Foote, http://jovial.com
 *
 */
package edu.calpoly.testy;
