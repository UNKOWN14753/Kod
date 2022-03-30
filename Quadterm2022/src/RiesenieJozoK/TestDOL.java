package RiesenieJozoK;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class TestDOL {
//	private static LISTTestScoring scoring = null;

	@BeforeClass
	public static void initScoring() {
//		scoring = new LISTTestScoring();
//		scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
	}

	
	
	static String[][] l1 = { { "A", "AB" }, { "B", "A" } };
	static String[][] l2 = { { "A", "ABA" }, { "B", "BBB" } };
	static String[][] l3 = { { "B", "BB" }, { "A", "B[A]A" } };
	static String[][] l4 = { { "A", "AA" }, { "B", "AB" } };
	static String[][] l5 = { { "C", "AA" }, { "B", "AB" } };
	static String[][] l6 = { { "A", "aA" }, { "B", "AB" } };
	static String[][] l7 = { { "A", "AA" }, { "A", "AB" } };

	@Test
	public void testKonstruktor() {
		new DOL(new String[][] { { "A", "AB" }, { "B", "A" } });
		new DOL(
				new String[][] { { "A", "ABA" }, { "B", "BBB" } });
//		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/6);
	}

	@Test
	public void testIsDOL() {
		assertTrue("isDOL: { { \"A\", \"AB\" }, { \"B\", \"A\" } }",
				new DOL(new String[][] { { "A", "AB" }, { "B", "A" } })
						.isDOL());
		assertTrue("isDOL: { { \"A\", \"ABA\" }, { \"B\", \"BBB\" } }",
				new DOL(
						new String[][] { { "A", "ABA" }, { "B", "BBB" } })
						.isDOL());
	//	assertFalse("isDOL: { { \"B\", \"BB\" }, { \"A\", \"B[A]A\" } }",
	//			new DOL(
	//					new String[][] { { "B", "BB" }, { "A", "B[A]A" } })
	//					.isDOL());
		assertTrue("isDOL: { { \"A\", \"AA\" }, { \"B\", \"AB\" } }",
				new DOL(new String[][] { { "A", "AA" }, { "B", "AB" } })
						.isDOL());
		assertFalse("isDOL: { { \"C\", \"AA\" }, { \"B\", \"AB\" } }",
				new DOL(new String[][] { { "C", "AA" }, { "B", "AB" } })
						.isDOL());
//		assertFalse("isDOL: { { \"A\", \"aA\" }, { \"B\", \"AB\" } }",
//				new DOL(new String[][] { { "A", "aA" }, { "B", "AB" } })
//						.isDOL());
		assertFalse("isDOL: { { \"A\", \"AA\" }, { \"A\", \"AB\" } }",
				new DOL(new String[][] { { "A", "AA" }, { "A", "AB" } })
						.isDOL());
//		scoring.updateScore("lang:common_list_test_scoring_name", 2*100.0/6);
	}

	static void assertEqualss(String msg, String s1, String s2) {
		assertTrue(msg,s1.equals(s2));
	}

	@Test
	public void testOneStep() {
		assertEqualss("oneStep: l1", "ABA", new DOL(l1).oneStep("AB"));
		assertEqualss("oneStep: l1", "AB", new DOL(l1).oneStep("A"));
		assertEqualss("oneStep: l1", "ABA", new DOL(l1).oneStep("AB"));
		assertEqualss("oneStep: l1", "ABAAB", new DOL(l1).oneStep("ABA"));
		assertEqualss("oneStep: l1", "ABAABABA",
				new DOL(l1).oneStep("ABAAB"));
		assertEqualss("oneStep: l1", "ABAABABAABAAB",
				new DOL(l1).oneStep("ABAABABA"));

		assertEqualss("oneStep: l2", "ABA", new DOL(l2).oneStep("A"));
		assertEqualss("oneStep: l2", "ABABBBABA",
				new DOL(l2).oneStep("ABA"));
		assertEqualss("oneStep: l2", "ABABBBABABBBBBBBBBABABBBABA",
				new DOL(l2).oneStep("ABABBBABA"));
		assertEqualss(
				"oneStep: l2",
				"ABABBBABABBBBBBBBBABABBBABABBBBBBBBBBBBBBBBBBBBBBBBBBBABABBBABABBBBBBBBBABABBBABA",
				new DOL(l2).oneStep("ABABBBABABBBBBBBBBABABBBABA"));

		assertEqualss("oneStep: l4", "AA", new DOL(l4).oneStep("A"));
		assertEqualss("oneStep: l4", "AAAA", new DOL(l4).oneStep("AA"));
		assertEqualss("oneStep: l4", "AAAAAAAA",
				new DOL(l4).oneStep("AAAA"));
		assertEqualss("oneStep: l4", "AAAAAAAAAAAAAAAA",
				new DOL(l4).oneStep("AAAAAAAA"));
//		scoring.updateScore("lang:common_list_test_scoring_name", 2*100.0/6);		
	}

	@Test
	public void testManySteps() {
		assertTrue("manySteps: l1", "ABAABABAABAAB".equals(
				new DOL(l1).manySteps(5, "A")));
		assertTrue(
				"manySteps: l2",
				"ABABBBABABBBBBBBBBABABBBABABBBBBBBBBBBBBBBBBBBBBBBBBBBABABBBABABBBBBBBBBABABBBABA".equals(
				new DOL(l2).manySteps(4, "A")));
		assertTrue("oneStep: l4", "AAAAAAAAAAAAAAAA".equals(
				new DOL(l4).manySteps(4, "A")));

		for (int i = 0; i < 20; i++)
			assertEquals("manySteps(" + i + ", l1).length()", fib(i),
					new DOL(l1).manySteps(i, "A").length());

		for (int i = 0; i < 10; i++)
			assertEquals("manySteps(" + i + ", l2).length()",
					(int) (Math.pow(3, i)), new DOL(l2)
							.manySteps(i, "A").length());

		for (int i = 0; i < 10; i++)
			assertEquals("manySteps(" + i + ", l4).length()",
					(int) (Math.pow(2, i)), new DOL(l4)
							.manySteps(i, "A").length());
//		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/6);
	}

	int fib(int n) {
		int a = 1;
		int b = 1;
		while (n-- > 0) {
			b += a;
			a = b - a;
		}
		return b;
	}
}
