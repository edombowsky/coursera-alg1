/**
 *
 * Author: Earl Dombowsky
 * Written: 2014.06.20
 *
 * Compilation: javac Percolation.java
 * Execution: java Percolation 100
 *
 * Implements percolation simulation for an N x N site matrix using weighted
 * quick union algorithm from algs4.jar library.
 *
 * Main method implements a small unit test. Argument must be provided to
 * define the size of the matrix to be used (N).
 *
 */

public class Percolation
{
    private final int N;
    private final int size;
    private final int virtualTop;
    private final int virtualBottom;;
    private final boolean[][] opened;
    private final WeightedQuickUnionUF ufTop;
    private final WeightedQuickUnionUF ufBottom;

    /**
     * Creates an N-by-N grid, with all sites blocked
     *
     * @param N
     */
    public Percolation(int N)
    {
        if (N <= 0) throw new IllegalArgumentException("gridSize (N) must be > 0");

        this.N    = N;
        this.size = (N * N) + 2;

        this.virtualTop    = 0;
        this.virtualBottom = size - 1;

        this.ufTop    = new WeightedQuickUnionUF(size);
        this.ufBottom = new WeightedQuickUnionUF(size);

        this.opened = new boolean[N + 1][N + 1];
    }

    /**
     *
     * @param i
     * @param j
     *
     * @throws java.lang.IndexOutOfBoundsException If a row index is out of bounds
     */
    private void validateIndices(int i, int j)
    {
        if (i <= 0 || i > N)
        {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
        if (j <= 0 || j > N)
        {
            throw new IndexOutOfBoundsException("column index j out of bounds");
        }
    }

    /**
     * Open site (row i, column j) if it is not already
     *
     * @param i
     * @param j
     */
    public void open(int i, int j)
    {
        validateIndices(i, j);

        if (!opened[i][j])
        {
            opened[i][j] = true;

            if ((i - 1 > 0) && opened[i - 1][j])
            {
                ufTop.union(xyTo1D(i, j), xyTo1D(i - 1, j));
                ufBottom.union(xyTo1D(i, j), xyTo1D(i - 1, j));
            }

            if ((i + 1 <= N) && opened[i + 1][j])
            {
                ufTop.union(xyTo1D(i, j), xyTo1D(i + 1, j));
                ufBottom.union(xyTo1D(i, j), xyTo1D(i + 1, j));
            }

            if ((j - 1 > 0) && opened[i][j - 1])
            {
                ufTop.union(xyTo1D(i, j), xyTo1D(i, j - 1));
                ufBottom.union(xyTo1D(i, j), xyTo1D(i, j - 1));
            }

            if ((j + 1 <= N) && opened[i][j + 1])
            {
                ufTop.union(xyTo1D(i, j), xyTo1D(i, j + 1));
                ufBottom.union(xyTo1D(i, j), xyTo1D(i, j + 1));
            }

            if (i == 1)
            {
                ufTop.union(xyTo1D(i, j), virtualTop);
                ufBottom.union(xyTo1D(i, j), virtualTop);
            }
            if (i == N)
                ufBottom.union(xyTo1D(i, j), virtualBottom);
        }
        // Debugging
//        if (percolates())
//        {
//            System.out.println("It percolates");
//        }
//        else
//        {
//            System.out.println("Does not percolate");
//        }

//        printArray(field);
    }


    /**
     * Is site (row i, column j) open?
     *
     * @param i
     * @param j
     * @return true if the site site at location i,j is open
     */
    public boolean isOpen(int i, int j)
    {
        validateIndices(i, j);

        return opened[i][j];
    }

    /**
     * Is site (row i, column j) full?
     *
     * @param i
     * @param j
     * @return true if the site at location i,j is full
     */
    public boolean isFull(int i, int j)
    {
        validateIndices(i, j);

        if (opened[i][j])
        {
            return ufTop.connected(xyTo1D(i, j), virtualTop);
        }

        return false;
    }

    /**
     *
     * @return true if the grid percolates
     */
    public boolean percolates()
    {
        return ufBottom.connected(virtualTop, virtualBottom);
    }


    /**
     * Print out matrix
     *
     * @param arr
     */
    private void printArray(boolean[][] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr.length; j++)
            {
                if (arr[i][j] == false)
                {
                    System.out.print(" 0 ");
                }
                else
                {
                    System.out.print(" X ");
                }
            }
            System.out.println();
        }
    }

    private int xyTo1D(int i, int j)
    {
        return (i - 1) * N + j;
    }

    /**
     * Unit test.
     *
     * @param args number of elements (N) in NxN matrix to use in test simulation
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        if (args.length < 1)
        {
            throw new Exception("Provide N as the first argument!");
        }

        int N = Integer.parseInt(args[0]);
        Percolation percolation = new Percolation(N);
        int midCol = N / 2;

        for (int i = 1; i <= N; i++)
        {
            percolation.open(i, midCol);
        }

        if (!percolation.isOpen(N/2, midCol))
        {
            throw new Exception("Test failed! Site should be opened!");
        }

        if (!percolation.isFull(N/2, midCol))
        {
            throw new Exception("Test failed! Site should be full!");
        }

        if (!percolation.percolates())
        {
            throw new Exception("Test failed! System should percolate!");
        }

        System.out.println("Test status: success!");
    }
}
