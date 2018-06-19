
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

public class Main {

    public void testPass1() {
	// Doing nothing passes
    }

    public void testFail1() {
	fail("Fail 1");
    }



    public static void main(String[] args) {
	final Main tests = new Main();
	final String same = "same string";
	final String same2 = same;
	int failed;

	System.out.println();

	//
	// Test failing, includin one  with no message (at the end).
	//
	failed = Testy.run(
	    () -> tests.testFail1(),
	    () -> assertTrue("assertTrue", false),
	    () -> assertFalse("assertFalse", true),
	    () -> assertEquals("assertEquals", "two", new String("one")),
	    () -> assertNotEquals("assertNotEquals", "one", new String("one")),
	    () -> assertSame("assertSame", same, new String(same)),
	    () -> assertNotSame("assertNotSame", same, same),
	    () -> assertNull("assertNull", "not null"),
	    () -> assertNotNull("assertNotNull", null),
	    () -> assertEquals("assertEquals boolean", true, false),
	    () -> assertNotEquals("assertNotEquals boolean", true, true),
	    () -> assertEquals("assertEquals boolean[]", 
	                       new boolean[] { true, false },
	                       new boolean[] { true, true }),
	    () -> assertNotEquals("assertNotEquals boolean[]", 
	                          new boolean[] { true, true },
	                          new boolean[] { true, true }),

	    () -> assertEquals("assertEquals byte", (byte) 1, (byte) 2),
	    () -> assertNotEquals("assertNotEquals byte", (byte) 1, (byte) 1),
	    () -> assertEquals("assertEquals byte[]", 
	                       new byte[] { (byte) 1, (byte) 1 },
	                       new byte[] { (byte) 1, (byte) 0 }),
	    () -> assertNotEquals("assertNotEquals byte[]", 
	                          new byte[] { (byte) 1, (byte) 1 },
	                          new byte[] { (byte) 1, (byte) 1 }),

	    () -> assertNotEquals("assertNotEquals char", 'a', 'a'),
	    () -> assertEquals("assertEquals char", 'a', 'b'),
	    () -> assertNotEquals("assertNotEquals char[]", 
	                       new char[] { 'a', 'a' },
	                       new char[] { 'a', 'a' }),
	    () -> assertEquals("assertEquals char[]", 
	                          new char[] { 'a', 'a' },
	                          new char[] { 'a', 'b' }),

	    () -> assertNotEquals("assertNotEquals int", 1, 1),
	    () -> assertEquals("assertEquals int", 1, 0),
	    () -> assertNotEquals("assertNotEquals int[]", 
	                       new int[] { 1, 1 },
	                       new int[] { 1, 1 }),
	    () -> assertEquals("assertEquals int[]", 
	                          new int[] { 1, 1 },
	                          new int[] { 1, 0 }),

	    () -> assertNotEquals("assertNotEquals long", 1L, 1L),
	    () -> assertEquals("assertEquals long", 1L, 0L),
	    () -> assertNotEquals("assertNotEquals long[]", 
	                       new long[] { 1L, 1L },
	                       new long[] { 1L, 1L }),
	    () -> assertEquals("assertEquals long[]", 
	                          new long[] { 1L, 1L },
	                          new long[] { 1L, 0L }),

	    () -> assertNotEquals("assertNotEquals double", 1.3, 1.301, 0.01),
	    () -> assertEquals("assertEquals double", 1.3, 1.4, 0.01),
	    () -> assertNotEquals("assertNotEquals double[]", 
	                       new double[] { 1.3, 1.3 },
	                       new double[] { 1.3, 1.3 }, 0.01),
	    () -> assertEquals("assertEquals double[]", 
	                          new double[] { 1.3, 1.3 },
	                          new double[] { 1.3, 1.5 }, 0.01),
	    () -> assertNotEquals("assertNotEquals double[][]", 
	                       new double[][] { { 1.3, 1.3 } },
	                       new double[][] { { 1.3, 1.3 } }, 0.01),
	    () -> assertEquals("assertEquals double[][]", 
	                          new double[][] { { 1.3, 1.3 } },
	                          new double[][] { { 1.3, 1.5 } }, 0.01),
	    () -> assertNotEquals("assertNotEquals double[][][]", 
	                       new double[][][] { { { 1.3, 1.3 } } },
	                       new double[][][] { { { 1.3, 1.3 } } }, 0.01),
	    () -> assertEquals("assertEquals double[][][]", 
	                          new double[][][] { { { 1.3, 1.3 } } },
	                          new double[][][] { { { 1.3, 1.5 } } }, 0.01),
	    () -> assertNotEquals("assertNotEquals double[][][][]", 
	                       new double[][][][] {{{{1.3, 1.3}}}},
	                       new double[][][][] {{{{1.3, 1.3}}}}, 0.01),
	    () -> assertEquals("assertEquals double[][][][]", 
	                          new double[][][][] {{{{1.3, 1.3}}}},
	                          new double[][][][] {{{{1.3, 1.5}}}}, 0.01),

	    () -> assertNotEquals("assertNotEquals float", 1.3f, 1.301f, 0.01f),
	    () -> assertEquals("assertEquals float", 1.3f, 1.4, 0.01f),
	    () -> assertNotEquals("assertNotEquals float[]", 
	                       new float[] { 1.3f, 1.3f },
	                       new float[] { 1.3f, 1.3f }, 0.01f),
	    () -> assertEquals("assertEquals float[]", 
	                          new float[] { 1.3f, 1.3f },
	                          new float[] { 1.3f, 1.5f }, 0.01f),
	    () -> assertNotEquals("assertNotEquals float[][]", 
	                       new float[][] { { 1.3f, 1.3f } },
	                       new float[][] { { 1.3f, 1.3f } }, 0.01f),
	    () -> assertEquals("assertEquals float[][]", 
	                          new float[][] { { 1.3f, 1.3f } },
	                          new float[][] { { 1.3f, 1.5f } }, 0.01f),
	    () -> assertNotEquals("assertNotEquals float[][][]", 
	                       new float[][][] { { { 1.3f, 1.3f } } },
	                       new float[][][] { { { 1.3f, 1.3f } } }, 0.01f),
	    () -> assertEquals("assertEquals float[][][]", 
	                          new float[][][] { { { 1.3f, 1.3f } } },
	                          new float[][][] { { { 1.3f, 1.5f } } }, 0.01f),
	    () -> assertNotEquals("assertNotEquals float[][][][]", 
	                       new float[][][][] {{{{1.3f, 1.3f}}}},
	                       new float[][][][] {{{{1.3f, 1.3f}}}}, 0.01f),
	    () -> assertEquals("assertEquals float[][][][]", 
	                          new float[][][][] {{{{1.3f, 1.3f}}}},
	                          new float[][][][] {{{{1.3f, 1.5f}}}}, 0.01f),

	    () -> assertEquals(new int[][] {{ 1, 2 }}, 
			       new int[][] {{ 3, 4 }})
	);
	System.out.println("Expected 50 failures.  Got:  " + failed);
	System.out.println();

	//
	// Test all versions without message
	//
	failed = Testy.run(
	    () -> tests.testPass1(),
	    () -> assertTrue(true),
	    () -> assertFalse(false),
	    () -> assertEquals("one", new String("one")),
	    () -> assertNotEquals("one", new String("two")),
	    () -> assertSame(same, same),
	    () -> assertNotSame("one", new String("one")),
	    () -> assertNull(null),
	    () -> assertNotNull("not null"),
	    () -> assertEquals(true, true),
	    () -> assertNotEquals(true, false),
	    () -> assertEquals(new boolean[] { true, true },
	                       new boolean[] { true, true }),
	    () -> assertNotEquals(new boolean[] { true, true },
	                          new boolean[] { true, false }),

	    () -> assertEquals((byte) 1, (byte) 1),
	    () -> assertNotEquals((byte) 1, (byte) 0),
	    () -> assertEquals(new byte[] { (byte) 1, (byte) 1 },
	                       new byte[] { (byte) 1, (byte) 1 }),
	    () -> assertNotEquals(new byte[] { (byte) 1, (byte) 1 },
	                          new byte[] { (byte) 1, (byte) 0 }),

	    () -> assertEquals('a', 'a'),
	    () -> assertNotEquals('a', 'b'),
	    () -> assertEquals(new char[] { 'a', 'a' },
	                       new char[] { 'a', 'a' }),
	    () -> assertNotEquals(new char[] { 'a', 'a' },
	                          new char[] { 'a', 'b' }),

	    () -> assertEquals(1, 1),
	    () -> assertNotEquals(1, 0),
	    () -> assertEquals(new int[] { 1, 1 },
	                       new int[] { 1, 1 }),
	    () -> assertNotEquals(new int[] { 1, 1 },
	                          new int[] { 1, 0 }),

	    () -> assertEquals(1L, 1L),
	    () -> assertNotEquals(1L, 0L),
	    () -> assertEquals(new long[] { 1L, 1L },
	                       new long[] { 1L, 1L }),
	    () -> assertNotEquals(new long[] { 1L, 1L },
	                          new long[] { 1L, 0L }),

	    () -> assertEquals(1.3, 1.301, 0.01),
	    () -> assertNotEquals(1.3, 1.4, 0.01),
	    () -> assertEquals(new double[] { 1.3, 1.3 },
	                       new double[] { 1.3, 1.3 }, 0.01),
	    () -> assertNotEquals(new double[] { 1.3, 1.3 },
	                          new double[] { 1.3, 1.5 }, 0.01),
	    () -> assertEquals(new double[][] { { 1.3, 1.3 } },
	                       new double[][] { { 1.3, 1.3 } }, 0.01),
	    () -> assertNotEquals(new double[][] { { 1.3, 1.3 } },
	                          new double[][] { { 1.3, 1.5 } }, 0.01),
	    () -> assertEquals(new double[][][] { { { 1.3, 1.3 } } },
	                       new double[][][] { { { 1.3, 1.3 } } }, 0.01),
	    () -> assertNotEquals(new double[][][] { { { 1.3, 1.3 } } },
	                          new double[][][] { { { 1.3, 1.5 } } }, 0.01),
	    () -> assertEquals(new double[][][][] {{{{1.3, 1.3}}}},
	                       new double[][][][] {{{{1.3, 1.3}}}}, 0.01),
	    () -> assertNotEquals(new double[][][][] {{{{1.3, 1.3}}}},
	                          new double[][][][] {{{{1.3, 1.5}}}}, 0.01),

	    () -> assertEquals(1.3f, 1.301f, 0.01f),
	    () -> assertNotEquals(1.3f, 1.4, 0.01f),
	    () -> assertEquals(new float[] { 1.3f, 1.3f },
	                       new float[] { 1.3f, 1.3f }, 0.01f),
	    () -> assertNotEquals(new float[] { 1.3f, 1.3f },
	                          new float[] { 1.3f, 1.5f }, 0.01f),
	    () -> assertEquals(new float[][] { { 1.3f, 1.3f } },
	                       new float[][] { { 1.3f, 1.3f } }, 0.01f),
	    () -> assertNotEquals(new float[][] { { 1.3f, 1.3f } },
	                          new float[][] { { 1.3f, 1.5f } }, 0.01f),
	    () -> assertEquals(new float[][][] { { { 1.3f, 1.3f } } },
	                       new float[][][] { { { 1.3f, 1.3f } } }, 0.01f),
	    () -> assertNotEquals(new float[][][] { { { 1.3f, 1.3f } } },
	                          new float[][][] { { { 1.3f, 1.5f } } }, 0.01f),
	    () -> assertEquals(new float[][][][] {{{{1.3f, 1.3f}}}},
	                       new float[][][][] {{{{1.3f, 1.3f}}}}, 0.01f),
	    () -> assertNotEquals(new float[][][][] {{{{1.3f, 1.3f}}}},
	                          new float[][][][] {{{{1.3f, 1.5f}}}}, 0.01f)
	);
	System.out.println("Expected 0 failures.  Got:  " + failed);
	System.out.println();

	//
	// Test passing
	//
	failed = Testy.run(
	    () -> tests.testPass1(),
	    () -> assertTrue("assertTrue", true),
	    () -> assertFalse("assertFalse", false),
	    () -> assertEquals("assertEquals", "one", new String("one")),
	    () -> assertNotEquals("assertNotEquals", "one", new String("two")),
	    () -> assertSame("assertSame", same, same),
	    () -> assertNotSame("assertNotSame", "one", new String("one")),
	    () -> assertNull("assertNull", null),
	    () -> assertNotNull("assertNotNull", "not null"),
	    () -> assertEquals("assertEquals boolean", true, true),
	    () -> assertNotEquals("assertNotEquals boolean", true, false),
	    () -> assertEquals("assertEquals boolean[]", 
	                       new boolean[] { true, true },
	                       new boolean[] { true, true }),
	    () -> assertNotEquals("assertNotEquals boolean[]", 
	                          new boolean[] { true, true },
	                          new boolean[] { true, false }),

	    () -> assertEquals("assertEquals byte", (byte) 1, (byte) 1),
	    () -> assertNotEquals("assertNotEquals byte", (byte) 1, (byte) 0),
	    () -> assertEquals("assertEquals byte[]", 
	                       new byte[] { (byte) 1, (byte) 1 },
	                       new byte[] { (byte) 1, (byte) 1 }),
	    () -> assertNotEquals("assertNotEquals byte[]", 
	                          new byte[] { (byte) 1, (byte) 1 },
	                          new byte[] { (byte) 1, (byte) 0 }),

	    () -> assertEquals("assertEquals char", 'a', 'a'),
	    () -> assertNotEquals("assertNotEquals char", 'a', 'b'),
	    () -> assertEquals("assertEquals char[]", 
	                       new char[] { 'a', 'a' },
	                       new char[] { 'a', 'a' }),
	    () -> assertNotEquals("assertNotEquals char[]", 
	                          new char[] { 'a', 'a' },
	                          new char[] { 'a', 'b' }),

	    () -> assertEquals("assertEquals int", 1, 1),
	    () -> assertNotEquals("assertNotEquals int", 1, 0),
	    () -> assertEquals("assertEquals int[]", 
	                       new int[] { 1, 1 },
	                       new int[] { 1, 1 }),
	    () -> assertNotEquals("assertNotEquals int[]", 
	                          new int[] { 1, 1 },
	                          new int[] { 1, 0 }),

	    () -> assertEquals("assertEquals long", 1L, 1L),
	    () -> assertNotEquals("assertNotEquals long", 1L, 0L),
	    () -> assertEquals("assertEquals long[]", 
	                       new long[] { 1L, 1L },
	                       new long[] { 1L, 1L }),
	    () -> assertNotEquals("assertNotEquals long[]", 
	                          new long[] { 1L, 1L },
	                          new long[] { 1L, 0L }),

	    () -> assertEquals("assertEquals double", 1.3, 1.301, 0.01),
	    () -> assertNotEquals("assertNotEquals double", 1.3, 1.4, 0.01),
	    () -> assertEquals("assertEquals double[]", 
	                       new double[] { 1.3, 1.3 },
	                       new double[] { 1.3, 1.3 }, 0.01),
	    () -> assertNotEquals("assertNotEquals double[]", 
	                          new double[] { 1.3, 1.3 },
	                          new double[] { 1.3, 1.5 }, 0.01),
	    () -> assertEquals("assertEquals double[][]", 
	                       new double[][] { { 1.3, 1.3 } },
	                       new double[][] { { 1.3, 1.3 } }, 0.01),
	    () -> assertNotEquals("assertNotEquals double[][]", 
	                          new double[][] { { 1.3, 1.3 } },
	                          new double[][] { { 1.3, 1.5 } }, 0.01),
	    () -> assertEquals("assertEquals double[][][]", 
	                       new double[][][] { { { 1.3, 1.3 } } },
	                       new double[][][] { { { 1.3, 1.3 } } }, 0.01),
	    () -> assertNotEquals("assertNotEquals double[][][]", 
	                          new double[][][] { { { 1.3, 1.3 } } },
	                          new double[][][] { { { 1.3, 1.5 } } }, 0.01),
	    () -> assertEquals("assertEquals double[][][][]", 
	                       new double[][][][] {{{{1.3, 1.3}}}},
	                       new double[][][][] {{{{1.3, 1.3}}}}, 0.01),
	    () -> assertNotEquals("assertNotEquals double[][][][]", 
	                          new double[][][][] {{{{1.3, 1.3}}}},
	                          new double[][][][] {{{{1.3, 1.5}}}}, 0.01),

	    () -> assertEquals("assertEquals float", 1.3f, 1.301f, 0.01f),
	    () -> assertNotEquals("assertNotEquals float", 1.3f, 1.4, 0.01f),
	    () -> assertEquals("assertEquals float[]", 
	                       new float[] { 1.3f, 1.3f },
	                       new float[] { 1.3f, 1.3f }, 0.01f),
	    () -> assertNotEquals("assertNotEquals float[]", 
	                          new float[] { 1.3f, 1.3f },
	                          new float[] { 1.3f, 1.5f }, 0.01f),
	    () -> assertEquals("assertEquals float[][]", 
	                       new float[][] { { 1.3f, 1.3f } },
	                       new float[][] { { 1.3f, 1.3f } }, 0.01f),
	    () -> assertNotEquals("assertNotEquals float[][]", 
	                          new float[][] { { 1.3f, 1.3f } },
	                          new float[][] { { 1.3f, 1.5f } }, 0.01f),
	    () -> assertEquals("assertEquals float[][][]", 
	                       new float[][][] { { { 1.3f, 1.3f } } },
	                       new float[][][] { { { 1.3f, 1.3f } } }, 0.01f),
	    () -> assertNotEquals("assertNotEquals float[][][]", 
	                          new float[][][] { { { 1.3f, 1.3f } } },
	                          new float[][][] { { { 1.3f, 1.5f } } }, 0.01f),
	    () -> assertEquals("assertEquals float[][][][]", 
	                       new float[][][][] {{{{1.3f, 1.3f}}}},
	                       new float[][][][] {{{{1.3f, 1.3f}}}}, 0.01f),
	    () -> assertNotEquals("assertNotEquals float[][][][]", 
	                          new float[][][][] {{{{1.3f, 1.3f}}}},
	                          new float[][][][] {{{{1.3f, 1.5f}}}}, 0.01f)
	);
	System.out.println("Expected 0 failures.  Got:  " + failed);
	System.out.println();
    }
}

