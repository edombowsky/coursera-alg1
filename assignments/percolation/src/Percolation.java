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
    private boolean[][] field;
    private int[][] intfield;
    private int gridSize;
    private WeightedQuickUnionUF connectionSolver;


    /**
     * Creates an N-by-N grid, with all sites blocked
     *
     * @param N
     */
    public Percolation(int N)
    {
        if (N <= 0) throw new IllegalArgumentException("gridSize must be > 0");

        gridSize = N;
        field = new boolean[N][N];
        intfield = new int[N][N];
        connectionSolver = new WeightedQuickUnionUF((gridSize * gridSize) + 2);
        createField();
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
        if (i <= 0 || i > gridSize)
        {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
        if (j <= 0 || j > gridSize)
        {
            throw new IndexOutOfBoundsException("column index j out of bounds");
        }
    }


    /**
     *
     */
    private void createField()
    {
        int index = 0;

        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                field[i][j] = false;
                intfield[i][j] = index++;
            }
        }

        createVirtualRoots();
    }

    /**
     *
     */
    private void createVirtualRoots()
    {
        for (int i = 0; i < gridSize; i++)
        {
            connectionSolver.union(intfield[0][i], gridSize * gridSize);
            connectionSolver.union(intfield[gridSize - 1][i], (gridSize * gridSize) + 1);
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

        field[i - 1][j - 1] = true;
        int k = i - 1;
        int l = j - 1;
        if (k - 1 >= 0 && field[k - 1][l])
        {
            connectionSolver.union(intfield[k - 1][l], intfield[k][l]);
        }

        if (k + 1 < gridSize && field[k + 1][l])
        {
            connectionSolver.union(intfield[k + 1][l], intfield[k][l]);
        }

        if (l - 1 >= 0 && field[k][l - 1])
        {
            connectionSolver.union(intfield[k][l - 1], intfield[k][l]);
        }

        if (l + 1 < gridSize && field[k][l + 1])
        {
            connectionSolver.union(intfield[k][l + 1], intfield[k][l]);
        }

        // Debugging
        if (percolates())
        {
            System.out.println("It percolates");
        }
        else
        {
            System.out.println("Does not percolate");
        }

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

        return field[i-1][j-1];
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

        if (connectionSolver.connected(intfield[i-1][j-1], gridSize * gridSize) && isOpen(i, j))
        {
            return true;
        }

        return false;
    }

    /**
     *
     * @return true if the grid percolates
     */
    public boolean percolates()
    {
        // does the system percolate?
        if (gridSize == 1)
        {
            return isOpen(1, 1);
        }

        return connectionSolver.connected(gridSize * gridSize, gridSize * gridSize + 1);
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
