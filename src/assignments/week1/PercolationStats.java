import java.util.Random;



public class PercolationStats {
    
    final double[] experiments;
    
    public PercolationStats(int N, int T) {
        // perform T independent computational experiments on an N-by-N grid
        if (N < 0 || T < 0) { throw new IllegalArgumentException(); }
        
        experiments = new double[T];
        for (int i=0; i<T; i++) {
            experiments[i] = Integer.MAX_VALUE;
        }
        
        for (int i=0; i<T; i++) {
            experiments[i] = doExperiment(N);
            System.out.println("exp# "+ i + " -> " + experiments[i]);
        }    
    }
    
    public double doExperiment(int N) {
        
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
    
    public double mean() {
        /*
        double sum = 0;
        for (int i=0; i<experiments.length; i++) {
           sum += experiments[i];
        }
        return sum / experiments.length;
        */
        return StdStats.mean(experiments);
    }
        
    public double stddev() {
        
        if (experiments.length == 1) { return Double.NaN; }
        /*
        double avg = mean();
        double sum = 0;
        for (int i=0; i<experiments.length; i++) {
            sum += (experiments[i] - avg) * (experiments[i] - avg);
        }
        return sum / (experiments.length-1);
        */
        return StdStats.stddev(experiments);
    }
    
    public double confidenceLo() {
        // returns lower bound of the 95% confidence interval
        double avg = mean();
        double var = Math.sqrt(stddev());
        return avg - (1.96 * var / Math.sqrt(experiments.length));
    }
    
    public double confidenceHi() {
        // returns upper bound of the 95% confidence interval
        double avg = mean();
        double var = Math.sqrt(stddev());
        return avg + (1.96 * var / Math.sqrt(experiments.length));
    }
    
    public static void main(String[] args) {
        
        //int N = Integer.parseInt(args[0]);
        //int T = Integer.parseInt(args[1]);
        int N = 200;
        int T = 100;
        PercolationStats foo = new PercolationStats(N, T);
        
        System.out.println("mean\t\t\t" + foo.mean());
        System.out.println("stddev\t\t\t" + foo.stddev());
        System.out.println("95% confidence interval\t" + foo.confidenceLo() + ", " + foo.confidenceHi());
    }
 }

