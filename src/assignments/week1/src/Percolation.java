public class Percolation {
    
    private final boolean[] grid, topConnected, bottomConnected;
    private final int size;
    private final WeightedQuickUnionUF foo;
    private boolean percolates = false;
    
    /**
     * Create N-by-N grid, with all sites blocked
     * @param N
     */
    public Percolation(int N) {
        
        if (N <= 0) { throw new IllegalArgumentException(); }
        
        grid = new boolean[N*N];
        topConnected = new boolean[N*N];
        bottomConnected = new boolean[N*N];
        
        for (int i = 0; i < N*N; i++) { 
            grid[i] = false; 
            topConnected[i] = false; 
            topConnected[i] = false; 
        }
        foo = new WeightedQuickUnionUF(N*N);
        size = N;
    } 
    
    /**
     * Open a site 
     * @param i row
     * @param j column
     */
    public void open(int i, int j) {
        
        checkBoundaries(i, j);
        
        // corner case size == 1
        if (size == 1 && i == 1 && j == 1) {
            grid[flatten(i, j)] = true;
            percolates = true;
            topConnected[0] = true;
            return;
        }
         
        int root = findRoot(i, j);
        // top?
        if (i == 1) {
            topConnected[root] = true;
        }
        // bottom?
        if (i == size) {
           bottomConnected[root] = true;
        }
        grid[flatten(i, j)] = Boolean.TRUE;
        
        // open top
        if (j != 1 && isOpen(i, j-1)) {
            mergeCells(i, j, i, j-1);
        }
        // open bottom
        if (j != size && isOpen(i, j+1)) {
            mergeCells(i, j, i, j+1);
        }
        // open left
        if (i != 1 && isOpen(i-1, j)) {
            mergeCells(i, j, i-1, j);
        }
        // open right
        if (i != size && isOpen(i+1, j)) {
            mergeCells(i, j, i+1, j);
        }
    }
    
    /**
     * Find the root of a site
     * @param i row 
     * @param j column
     * @return
     */
    private int findRoot(int i, int j) {
        return foo.find(flatten(i, j));
    }
    /**
     * Merge two sites. 
     * Takes also care of setting the top and bottom connected flags.
     * Finally it checks for percolation and set the flag if needed.
     *  
     * @param i row site 1
     * @param j column site 1
     * @param s row site 2
     * @param t column site 2
     */
    private void mergeCells(int i, int j, int s, int t) {
       
        boolean isTopConnected = false;
        boolean isBoottomConnected = false;
        
        int rootA = findRoot(i, j);
        int rootB = findRoot(s, t);
        
        if (topConnected[rootA] || topConnected[rootB]) {
            isTopConnected = true;
        }
        if (bottomConnected[rootA] || bottomConnected[rootB]) {
            isBoottomConnected = true;
        }
        
        foo.union(flatten(i, j), flatten(s, t));
        
        int rootNewA = findRoot(i, j);
        
        if (isTopConnected)     { topConnected[rootNewA]    = true; }
        if (isBoottomConnected) { bottomConnected[rootNewA] = true; }
       
        // percolates?
        int rootNew = findRoot(i, j);
        if (topConnected[rootNew] && bottomConnected[rootNew]) {
            percolates = true;
        }
    }
    
    /**
     * Is this site open?
     * @param i row
     * @param j column
     * @return
     */
    public boolean isOpen(int i, int j) {
        checkBoundaries(i, j);
        return grid[flatten(i, j)];
    }
    
    /**
     * Is this site full?
     * @param i row
     * @param j column
     * @return
     */
    public boolean isFull(int i, int j) {
        checkBoundaries(i, j);
        return topConnected[foo.find(flatten(i, j))];
    }
    
    /** 
     * Does the system percolate?
     */
    public boolean percolates() {
        return percolates;
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
    
    /**
     * Check the given coordinates correspond to a valid site
     * 
     * @param i row
     * @param j column
     */
    private void checkBoundaries(int i, int j) {
        
        if (i < 1 || j < 1 || j > size || i > size) {
            throw new IndexOutOfBoundsException();
        }
    }
 }