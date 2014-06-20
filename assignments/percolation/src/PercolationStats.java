/**
 *
 * Author: Earl Dombowsky
 * Written: 2014.06.20
 *
 * Compilation: javac PercolationStats.java
 * Execution: java PercolationStats 200 100
 *
 * Runs percolation experiment T times for an N x N site matrix and prints out
 * the mean, standard deviation, and the 95% confidence interval for the
 * percolation threshold. Use standard random from our standard libraries to
 * generate random numbers; use standard statistics to compute the sample mean
 * and standard deviation.
 *
 */

public class PercolationStats
{
    private double[] results;           // Contains computer thresholds for T iterations
    private double   mean;              // Computed mean value for the results array
    private double   stddev;            // Computed standard dev. for the results array
    private double   confidenceLow;     // 95% confidence value lower bound
    private double   confidenceHigh;    // 95% confidence value higher bound

    /**
     * perform T independent computational experiments on an N-by-N grid
     *
     * @param N size of the matrix to be used
     * @param T the number of times to run the simulation
     */
    public PercolationStats(int N, int T)
    {
        if (N <= 0 || T <= 0)
        {
            throw new IllegalArgumentException("N="+N+" T="+T);
        }

        results = new double[T];
        int row = 0; // randomly generated row in the grid
        int col = 0; // randomly generated column in the grid
        int openSiteCounter = 0;

        // Repeat simulation T times
        for (int i = 0; i < T; i++)
        {
            openSiteCounter = 0;
            Percolation percolation = new Percolation(N);

            // Repeat opening sites randomly until system percolates
            do {
                // Generate randomly next site
                row = StdRandom.uniform(1, N + 1);
                col = StdRandom.uniform(1, N + 1);

                // Open site if not already opened
                if (!percolation.isOpen(row, col))
                {
                    percolation.open(row, col);
                    ++openSiteCounter;
                }

            } while (!percolation.percolates());

            // Compute threshold and save the result for the iteration
            results[i] = openSiteCounter / (double) (N * N);
        }

        // Compute mean threshold, standard dev. and 95% confidence boundaries.
        mean            = StdStats.mean(results);
        stddev          = StdStats.stddev(results);
        double delta    = 1.96 * stddev / Math.sqrt(T);
        confidenceLow   = mean - delta;
        confidenceHigh  = mean + delta;
    }

    /**
     * @return sample mean of percolation threshold
     */
    public double mean()
    {
        return mean;
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev()
    {
        return stddev;
    }

    /**
     * @return lower bound of the 95% confidence interval
     */
    public double confidenceLo()
    {
        return confidenceLow;
    }

    /**
     * @return upper bound of the 95% confidence interval
     */
    public double confidenceHi()
    {
        return confidenceHigh;
    }

    /**
     * Monte Carlo simulation. To estimate the percolation threshold,
     * consider the following computational experiment:
     * <p/>
     * <ul>
     * <li>Initialize all sites to be blocked.</li>
     * <li>Repeat the following until the system percolates:
     * <ul>
     * <li>Choose a site (row i, column j) uniformly at random among all blocked sites.</li>
     * <li>Open the site (row i, column j).</li>
     * <li>The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.</li>
     * </ul>
     * </li>
     * </ul>
     * <p/>
     * <p/>
     * Takes two command-line arguments N and T, performs T independent
     * computational experiments (discussed above) on an N-by-N grid,
     * and prints out the mean, standard deviation, and the 95%
     * confidence interval for the percolation threshold. Use standard
     * random from our standard libraries to generate random numbers; use
     * standard statistics to compute the sample mean and standard deviation.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO implement
        if (args.length != 2)
        {
            throw new IllegalArgumentException("Provide 2 arguments: main(T,N)=>"
                    + "(T=num iterations, N=num elements)");
        }

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        Stopwatch sw = new Stopwatch();

        PercolationStats percStats = new PercolationStats(N, T);

        // Print the results
//        System.out.format("elapsed time            = %f", sw.elapsedTime());
//        System.out.println();
//        System.out.format("mean                    = %.16f", percStats.mean());
//        System.out.println();
//        System.out.format("stddev                  = %.16f", percStats.stddev());
//        System.out.println();
//        System.out.format("95%% confidence interval = %.16f, %.16f",
//                percStats.confidenceLow, percStats.confidenceHigh);
//        System.out.println();


        System.out.println("elapsed time            = " + sw.elapsedTime());
        System.out.println("mean                    = " + percStats.mean());
        System.out.println("stddev                  = " + percStats.stddev());
        System.out.println("95% confidence interval = " + percStats.confidenceLo() + ", "
                                                        + percStats.confidenceHi());
    }
}
