import org.junit.Test;

import static org.junit.Assert.*;

public class TestRekurzia {
    @Test
    public void testFoo1() {
        int MAX = 6;
        for(int i = 0; i < MAX; i++)
            for(int j = 0; j < MAX; j++)
                assertEquals("foo1", Rekurzia.foo(i,j), Rekurzia.foo1(i,j));
    }
    @Test
    public void testFoo2() {
        int MAX = 6;
        for(int i = 0; i < MAX; i++)
            for(int j = 0; j < MAX; j++)
                assertEquals("foo2", Rekurzia.foo(i,j), Rekurzia.foo2(i,j));
    }
    @Test
    public void testFoo() {
        int MAX = 6;
        for(int i = 0; i < MAX; i++)
            for(int j = 0; j < MAX; j++) {
                if (i + j < 2) continue;
                if (i > 0)
                    assertEquals("vztah " + i + j, Rekurzia.foo(i, j), (i + j + 1) * Rekurzia.foo1(i - 1, j));
                if (j > 0)
                    assertEquals("vztah" + i + j, Rekurzia.foo(i, j), (i + j + 1) * Rekurzia.foo1(i, j -1 ));
            }
    }

    @Test
    public void testFoo3() {
        int MAX = 6;
        for(int i = 0; i < MAX; i++)
            for(int j = 0; j < MAX; j++)
                assertEquals("foo3", Rekurzia.foo(i,j), Rekurzia.foo3(i,j));
    }

    @Test
    public void testFoo4() {
        int MAX = 6;
        for(int i = 0; i < MAX; i++)
            for(int j = 0; j < MAX; j++)
                assertEquals("foo4", Rekurzia.foo(i,j), Rekurzia.foo4(i,j));
    }

}