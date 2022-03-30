package RiesenieJozoK;
//import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestQueens {
//    private static LISTTestScoring scoring = null;

    @BeforeClass
    public static void initScoring() {
//        scoring = new LISTTestScoring();
//        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }


    static Queens q4 = new Queens(new int[]{2, 0, 3, 1});
    static Queens q6 = new Queens(new int[]{1, 3, 5, 0, 2, 4});
    static Queens q8 = new Queens(new int[]{3, 7, 0, 4, 6, 1, 5, 2});
    static Queens[] queens = new Queens[]{q4, q6, q8};

    @Test
    public void testConstructor() {
        int[] perm = new int[]{0, 1, 2, 3, 4};
        Queens q1 = new Queens(perm);
        perm[0] = 1;
        perm[1] = 0;
        Queens q2 = new Queens(perm);
        assertNotEquals("konstruktor si nevytvori kopiu pola", q1.toString(), q2.toString());
        assertNotEquals("konstruktor si nevytvori kopiu pola", q1.riesenie, q2.riesenie);
//        scoring.updateScore("lang:common_list_test_scoring_name",15);
    }

    @Test
    public void testToString() {
        int[] perm = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Queens q = new Queens(perm);
        for (int e : perm) {
            if (!q.toString().contains("" + e))
                fail("toString nevypisuje vsetky prvky riesenia");
        }
//        scoring.updateScore("lang:common_list_test_scoring_name",15);
    }


    @Test
    public void comparable() {
        // Queens implements Comparable
        assertTrue("Queens neimplementuje interface Comparable!",
                q8 instanceof Comparable);
        //System.out.println(Arrays.toString(Queens.class.getGenericInterfaces()));
        // Queens implements Comparable  VS  Queens implements Comparable<Queens>
        assertTrue("'Queens implements Comparable' malo byt 'Queens implements Comparable<T>'\n"
                        + "Nasledovny kod by sa nemal dat skompilovat:\n"
                        + "new Queens<>(null).compareTo(new Object());",
                Queens.class.getGenericInterfaces()[0].toString().equals("java.lang.Comparable<Queens>"));
//        scoring.updateScore("lang:common_list_test_scoring_name",10);
    }

    @Test
    public void compareTo() {
        Queens[][] qs = {{new Queens(new int[]{0, 1, 2, 3, 4}), new Queens(new int[]{0, 1, 2, 3, 4})},
                {new Queens(new int[]{0, 1, 2, 3, 4}), new Queens(new int[]{0, 2, 1, 3, 4})},
                {new Queens(new int[]{0, 1, 2, 3, 4}), new Queens(new int[]{0, 1, 2, 4, 3})},
                {new Queens(new int[]{0, 4, 2, 3, 1}), new Queens(new int[]{0, 1, 2, 3, 4})},
                {new Queens(new int[]{0, 1, 2, 4, 3}), new Queens(new int[]{0, 1, 2, 3, 4})}};
        int[] res = {0, -1, -1, 1, 1};
        for (int i = 0; i < qs.length; i++) {
            Queens q1 = qs[i][0];
            Queens q2 = qs[i][1];
            if(q1 instanceof Comparable){
                Comparable c1 = (Comparable) q1;
                assertEquals("compareTo", res[i], Integer.signum(c1.compareTo(q2)));
            }
            else {
                fail("Queens neimplementuje interface Comparable!");
            }
        }
//        scoring.updateScore("lang:common_list_test_scoring_name",10);
    }

    @Test
    public void clockwise90() {
        for (Queens q : queens) {
            assertArrayEquals("4xclockwise90",
                    q.riesenie,
                    q.clockwise90().clockwise90().clockwise90().clockwise90().riesenie);

        }
        assertArrayEquals("clockwise90", q4.riesenie, q4.clockwise90().riesenie);

        int[] vysledok_q6_clockwise90 = {2, 5, 1, 4, 0, 3};
        assertArrayEquals("clockwise90",
                vysledok_q6_clockwise90,
                q6.clockwise90().riesenie);

        assertArrayEquals("2xclockwise90", q6.riesenie, q6.clockwise90().clockwise90().riesenie);

        System.out.println(q8);
        System.out.println(Arrays.toString(q8.clockwise90().riesenie));
        int[] vysledok_q8_clockwise90 = {1, 4, 6, 3, 0, 7, 5, 2};
        assertArrayEquals("clockwise90",
                vysledok_q8_clockwise90,
                q8.clockwise90().riesenie);

        int[] vysledok_q8_2xclockwise90 = {5, 2, 6, 1, 3, 7, 0, 4};
        assertArrayEquals("2xclockwise90",
                vysledok_q8_2xclockwise90,
                q8.clockwise90().clockwise90().riesenie);

        int[] vysledok_q8_3xclockwise90 = {5, 2, 0, 7, 4, 1, 3, 6};
        assertArrayEquals("3xclockwise90",
                vysledok_q8_3xclockwise90,
                q8.clockwise90().clockwise90().clockwise90().riesenie);
//        scoring.updateScore("lang:common_list_test_scoring_name",20);
    }

    @Test
    public void horizontalFlip() {
        for (Queens q : queens) {
            assertArrayEquals("horizontalFlip",
                    q.riesenie,
                    q.horizontalFlip().horizontalFlip().riesenie);
        }
        int[] vysledok_q4_horizontalFlip = {1, 3, 0, 2};
        assertArrayEquals("horizontalFlip",
                vysledok_q4_horizontalFlip,
                q4.horizontalFlip().riesenie);

        int[] vysledok_q6_horizontalFlip = {4, 2, 0, 5, 3, 1};
        assertArrayEquals("horizontalFlip",
                vysledok_q6_horizontalFlip,
                q6.horizontalFlip().riesenie);

        int[] vysledok_q8_horizontalFlip = {4, 0, 7, 3, 1, 6, 2, 5};
        assertArrayEquals("horizontalFlip",
                vysledok_q8_horizontalFlip,
                q8.horizontalFlip().riesenie);
//        scoring.updateScore("lang:common_list_test_scoring_name",20);
    }

    @Test
    public void veritcalFlip() {
        for (Queens q : queens) {
            assertArrayEquals("veritcalFlip",
                    q.riesenie,
                    q.verticalFlip().verticalFlip().riesenie);
        }
        int[] vysledok_q4_verticalFlip = {1, 3, 0, 2};
        assertArrayEquals("verticalFlip",
                vysledok_q4_verticalFlip,
                q4.verticalFlip().riesenie);

        int[] vysledok_q6_verticalFlip = {4, 2, 0, 5, 3, 1};
        assertArrayEquals("verticalFlip",
                vysledok_q6_verticalFlip,
                q6.verticalFlip().riesenie);

        int[] vysledok_q8_verticalFlip = {2, 5, 1, 6, 4, 0, 7, 3};
        assertArrayEquals("verticalFlip",
                vysledok_q8_verticalFlip,
                q8.verticalFlip().riesenie);
//        scoring.updateScore("lang:common_list_test_scoring_name",10);
    }

}