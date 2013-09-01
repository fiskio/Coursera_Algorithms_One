package week1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PathCompressionQuickUnionUFTest {

    PathCompressionQuickUnionUF foo;

    @Before
    public void setUp() throws Exception {
        foo = new PathCompressionQuickUnionUF(10);
    }

    @After
    public void tearDown() throws Exception {
        foo = null;
    }

    @Test
    public void testCount() throws Exception {

        assertEquals(10, foo.count());

        foo.connect(1, 2);
        assertEquals(9, foo.count());

        foo.connect(3, 4);
        assertEquals(8, foo.count());

        foo.connect(5, 6);
        assertEquals(7, foo.count());

        foo.connect(1, 6);
        assertEquals(6, foo.count());

        foo.connect(1, 2);
        assertEquals(6, foo.count());
    }

    @Test
    public void testConnected() throws Exception {

        assertFalse(foo.isConnected(1, 2));
        foo.connect(1, 2);
        assertTrue(foo.isConnected(1, 2));

        assertFalse(foo.isConnected(3, 4));
        foo.connect(3, 4);
        assertTrue(foo.isConnected(3, 4));

        assertFalse(foo.isConnected(5, 6));
        foo.connect(5, 6);
        assertTrue(foo.isConnected(5, 6));
    }

}
