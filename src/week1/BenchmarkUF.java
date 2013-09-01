package week1;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class BenchmarkUF {

    private static final int ITER = 10_000_000; // # of isConnected calls
    private static final int SIZE = 10_000_000; // # of nodes
    private static final int CONN = 50;         // % of connectivity

    public long benchmark(UnionFinder uf) {

        System.out.println("Benchmarking: [" + uf.getClass().getSimpleName() + "]");

        Random rand = new Random(69);

        int conn = (int) SIZE * CONN / 100;

        System.out.println("Size: " + SIZE + " - Connections: " + conn);

        /* connect */

        long start = System.nanoTime();

        for (int i=0; i< conn; i++) {
            int a = rand.nextInt(SIZE);
            int b = rand.nextInt(SIZE);
            uf.connect(a, b);
        }

        long end = System.nanoTime();
        long elapsed = TimeUnit.MILLISECONDS.convert(end-start, TimeUnit.NANOSECONDS);

        System.out.println("Connected " + CONN + "% of the nodes in " + elapsed + " ms");

        /* isConnected */

        start = System.nanoTime();

        for (int i=0; i<ITER; i++) {
            int a = rand.nextInt(SIZE);
            int b = rand.nextInt(SIZE);
            uf.isConnected(a, b);
        }

        end = System.nanoTime();
        elapsed = TimeUnit.MILLISECONDS.convert(end-start, TimeUnit.NANOSECONDS);

        System.out.println("Run isConnected " + ITER + " times in " + elapsed + " ms\n");

        return elapsed;
    }

    public void benchmarkFile(String fn) {

        System.out.println("Benchmark File: " + fn);

        try {
            int N = Integer.parseInt(new Scanner(new File(fn)).nextLine());
            System.out.println("#nodes: " + N);

            // QuickFind
            // benchmarkConnect(new QuickFindUF(N), new Scanner(new File(fn)));

            // QuickUnion
            // benchmarkConnect(new QuickUnionUF(N), new Scanner(new File(fn)));

            // WeightedQuickUnion
            benchmarkConnect(new WeightedQuickUnionUF(N),  new Scanner(new File(fn)));

            // PathCompressionQuickUnion
            benchmarkConnect(new PathCompressionQuickUnionUF(N),  new Scanner(new File(fn)));

         } catch (Exception e) {
              e.printStackTrace();
        }
    }

    public void benchmarkConnect(UnionFinder uf, Scanner scanner) {


        int count = 0;
        int N = Integer.parseInt(scanner.nextLine());
        long start = System.nanoTime();

        while (scanner.hasNext() && count < 2_000_000) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            uf.connect(a, b);
            count++;
        }

        long end = System.nanoTime();
        long elapsed = TimeUnit.MILLISECONDS.convert(end-start, TimeUnit.NANOSECONDS);

        System.out.println("[" + uf.getClass().getSimpleName() + "] connected " + count + " times is " + elapsed + " ms\n");
        //scanner.reset();
    }

    public static void main(String[] args) {

        // new BenchmarkUF().benchmarkFile("assets/week1/tinyUF.txt");
        // new BenchmarkUF().benchmarkFile("assets/week1/mediumUF.txt");
        new BenchmarkUF().benchmarkFile("assets/week1/largeUF.txt");


        // new BenchmarkUF().benchmark(new QuickFindUF(SIZE));
        // new BenchmarkUF().benchmark(new QuickUnionUF(SIZE));
        // new BenchmarkUF().benchmark(new WeightedQuickUnionUF(SIZE));
        // new BenchmarkUF().benchmark(new PathCompressionQuickUnionUF(SIZE));
    }

}
