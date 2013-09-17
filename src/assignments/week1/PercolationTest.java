import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PercolationTest {

        Percolation foo;
        
        @Before
        public void setUp() throws Exception {
            foo = new Percolation(3);
        }

        @After
        public void tearDown() throws Exception {
            foo = null;
        }

        @Test
        public void testOpen() throws Exception {
            foo.open(1, 1);
            assertTrue(foo.isOpen(1, 1));
            assertTrue(foo.isFull(1, 1));
            
            foo.open(1, 2);
            assertTrue(foo.isOpen(1, 2));
            assertTrue(foo.isFull(1, 2));
            
            foo.open(2, 2);
            assertTrue(foo.isOpen(2, 2));
            assertTrue(foo.isFull(2, 2));
            
            foo.open(3, 2);
            assertTrue(foo.isOpen(3, 2));
            assertTrue(foo.isFull(3, 2));
            
            foo.open(3, 3);
            assertTrue(foo.isOpen(3, 3));
            assertTrue(foo.isFull(3, 3));
            
            assertTrue(foo.percolates());
            
            assertFalse(foo.isFull(2, 3));
            assertFalse(foo.isFull(1, 3));          
        }
        
}
