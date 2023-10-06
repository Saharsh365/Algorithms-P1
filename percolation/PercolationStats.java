/* *****************************************************************************
 *  Name:              Saharsh Maloo
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double Z_STAR = 1.96;
    private double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniformInt(1, n + 1), StdRandom.uniformInt(1, n + 1));
            }
            results[i] = (double) perc.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (Z_STAR * stddev() / Math.sqrt(results.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (Z_STAR * stddev() / Math.sqrt(results.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percs = new PercolationStats(Integer.parseInt(args[0]),
                                                      Integer.parseInt(args[1]));
        StdOut.println("mean\t= " + percs.mean());
        StdOut.println("stddev\t= " + percs.stddev());
        StdOut.println(
                "95% confidence interval\t= [" + percs.confidenceLo() + ", " + percs.confidenceHi()
                        + "]");
    }

}
