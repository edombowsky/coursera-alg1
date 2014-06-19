/**
 * Created by CAEADOM on 6/19/14.
 */
public class PercolationStats {
    /**
     * perform T independent computational experiments on an N-by-N grid
     *
     * @param N grid width
     * @param T grid height
     */
    public PercolationStats(int N, int T) {
        //TODO implement
    }

    /**
     * @return sample mean of percolation threshold
     */
    public double mean() {
        // TODO implement
        return 0.0;
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        // TODO implement
        return 0.0;
    }

    /**
     * @return lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        // TODO implement
        return 0.0;
    }

    /**
     * @return upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        // TODO implement
        return 0.0;
    }

    /**
     * Monte Carlo simulation. To estimate the percolation threshold, consider the following computational experiment:
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
     * Takes two command-line arguments N and T, performs T independent computational experiments (discussed above)
     * on an N-by-N grid, and prints out the mean, standard deviation, and the 95% confidence interval for the
     * percolation threshold. Use standard random from our standard libraries to generate random numbers; use
     * standard statistics to compute the sample mean and standard deviation.
     *
     * @param args
     */
    public static void main(String[] args) {
        // TODO implement
    }
}
