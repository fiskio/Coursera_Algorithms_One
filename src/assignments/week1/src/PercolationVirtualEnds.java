public class PercolationVirtualEnds {
    
    private final boolean[] grid;
    private final int size, virtualTop, virtualBottom; 
    private final WeightedQuickUnionUF foo;
    
    // create N-by-N grid, with all sites blocked
    public PercolationVirtualEnds(int N) {
        
        if (N < 0) { throw new IllegalArgumentException(); }
        
        grid = new boolean[N*N];
        // Arrays.fill(grid, Boolean.FALSE); // FALSE = blocked
        for (int i = 0; i < N*N; i++) { 
            grid[i] = false; 
        }
        foo = new WeightedQuickUnionUF(N*N+2);
        size = N;
        virtualTop = N*N;
        virtualBottom = N*N+1;
    } 
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        
        checkBoundaries(i, j);
        
        grid[flatten(i, j)] = Boolean.TRUE;
        
        int origin = flatten(i, j);
        
        // open top
        if (j != 1 && isOpen(i, j-1)) {
            foo.union(flatten(i, j-1), origin);
        }
        // open bottom
        if (j != size && isOpen(i, j+1)) {
            foo.union(flatten(i, j+1), origin);
        }
        // open left
        if (i != 1 && isOpen(i-1, j)) {
            foo.union(origin-1, origin);
        }
        // open right
        if (i != size && isOpen(i+1, j)) {
            foo.union(origin+1, origin);
        }
        
        // virtual top?
        if (i == 1) {
            foo.union(flatten(i, j), virtualTop);
        }
        
        // virtual bottom?
        if (i == size) {
            foo.union(flatten(i, j), virtualBottom);
        }
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        
        checkBoundaries(i, j);
        return grid[flatten(i, j)];
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        checkBoundaries(i, j);

        /*
        if (j == 1) { return true; }
        
        for (int k=0; k<size-1; k++) {
            if (foo.connected(k, flatten(i,j))) {
                return true;
            }
        }
        return false;
        */
        return foo.connected(virtualTop, flatten(i, j));
    }
    
    // does the system percolate?
    public boolean percolates() {
        /*
        for (int k=1; k<=size; k++) {
            if (isFull(k, size)) {
                return true;
            }
        }
        return false;
        */
        return foo.connected(virtualTop, virtualBottom);
    }
    
    /**
     * Converts a two dimensional coordinate (starting index 1) 
     * to its one dimensional equivalent (starting index 0)
     * @param x row
     * @param y column
     * @return
     */
    private int flatten(int x, int y) {
        return size * (y-1) + (x-1);
    }
    
    private void checkBoundaries(int i, int j) {
        
        if (i < 1 || j < 1 || j > size || i > size) {
            throw new IndexOutOfBoundsException();
        }
    }
 }