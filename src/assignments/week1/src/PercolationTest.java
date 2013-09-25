import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PercolationTest {

        private Percolation foo;

        @Test
        public void testOne() throws Exception {
            
            foo = new Percolation(1);
            
            foo.open(1, 1);
            assertTrue(foo.isOpen(1, 1));
            assertTrue(foo.percolates());
        }

        @Test
        public void testOpen() throws Exception {
            
            foo = new Percolation(3);
            
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
