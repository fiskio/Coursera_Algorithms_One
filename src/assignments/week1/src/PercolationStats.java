public class PercolationStats {
    
    private final double[] experiments;
    
    /**
     * Perform T independent computational experiments on an N-by-N grid
     * @param N side length of the experimental grid
     * @param T number of experiments
     */
    public PercolationStats(int N, int T) {

        if (N <= 0 || T <= 0) { throw new IllegalArgumentException(); }
        
        experiments = new double[T];
        for (int i = 0; i < T; i++) {
            experiments[i] = Integer.MAX_VALUE;
        }
        
        long begin = System.currentTimeMillis();
        
        for (int i = 0; i < T; i++) {
            experiments[i] = doExperiment(N);
            System.out.println("exp# "+ i + " -> " + experiments[i]);
        }    
        
        long end = System.currentTimeMillis();
        System.out.println("TIME: " + (end - begin));
    }
    
    /**
     * Perform one experiment on a N-by-N grid
     * @param N side length of the experimental grid
     * @return percolation threshold
     */
    private double doExperiment(int N) {
        
        Percolation foo = new Percolation(N);
        double count = 0;
        
        while (!foo.percolates()) {
            int x = 1 + StdRandom.uniform(N);
            int y = 1 + StdRandom.uniform(N);
            if (!foo.isOpen(x, y)) {
                foo.open(x, y);
                count++;
            }
        }
        return count / (N*N);
    }
    
    /**
     * Calculates the arithmetic mean 
     * of the percolation thresholds of all experiments.
     * @return
     */
    public double mean() {
        return StdStats.mean(experiments);
    }
    
    /**
     * Calculates the standard deviation 
     * of the percolation thresholds of all experiments.  
     * @return
     */
    public double stddev() {
        if (experiments.length == 1) { return Double.NaN; }
        return StdStats.stddev(experiments);
    }
    
    /**
     * Calculates the lower bound of the 95% confidence interval
     * @return
     */
    public double confidenceLo() {
        return mean() - 1.96 * stddev();
    }
    
    /**
     * Calculates the higher bound of the 95% confidence interval
     * @return
     */
    public double confidenceHi() {
        return mean() + 1.96 * stddev();
    }
    
    
    public static void main(String[] args) {
        
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats foo = new PercolationStats(N, T);
        
        System.out.println("mean\t\t\t" + foo.mean());
        System.out.println("stddev\t\t\t" + foo.stddev());
        System.out.println("95% confidence interval\t" 
                + foo.confidenceLo() + ", " + foo.confidenceHi());
    }
 }

