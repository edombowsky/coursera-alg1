/*************************************************************************
 * Name: Earl Dombowsky
 * Email:
 *
 * Compilation: javac Board.java
 * Execution:
 *
 * Description: An immutable data type for boards of 8Puzzle problem.
 *
 *************************************************************************/

public class Board
{
    private int N;                  // Dimensions of puzzle board
    private byte[][] tiles = null;  // Puzzle board

    /**
     * Construct a board from an N-by-N array of blocks (where
     * blocks[i][j] = block in row i, column j).
     *
     * @param blocks  a two dimensional integer array representing the puzzle
     *                board
     */
    public Board(int[][] blocks)
    {
        N = blocks.length;

        if (N < 2 || N >= 128)
        {
            throw new IndexOutOfBoundsException("size not supported : " + N);
        }

        this.tiles = new byte[N][N];

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                tiles[i][j] = (byte) blocks[i][j];
            }
        }

    }

    private Board(byte[][] blocks)
    {
        this.tiles = copySquareArray(blocks);

        N = tiles.length;
    }


    /**
     * Board dimension N
     *
     * @return  the dimension of the board
     */
    public int dimension()
    {
        return N;
    }

    /**
     * Number of blocks out of place.
     *
     * For example, the Hamming priorities of the initial search node below is 5.
     *
     * 8  1  3      1  2  3     1  2  3  4  5  6  7  8     1  2  3  4  5  6  7  8
     * 4     2      4  5  6     ----------------------     ----------------------
     * 7  6  5      7  8  1     1  0  0  1  1  0  1  1     2  0  0  2  2  0  3
     *
     * initial       goal           Hamming = 5 + 0          Manhattan = 10 + 0
     *
     * @return  the hamming priority
     */
    public int hamming()
    {
        int hamming = 0;

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (tiles[i][j] == 0) continue;
                if (tiles[i][j] != (N * i + j + 1)) hamming++;
            }
        }

        return hamming;
    }

    /**
     * Sum of Manhattan distances between blocks and goal
     *
     * For example, the Manhattan priorities of the initial search node below is 10.
     *
     * 8  1  3      1  2  3     1  2  3  4  5  6  7  8
     * 4     2      4  5  6     ----------------------
     * 7  6  5      7  8  1     1  2  0  0  2  2  0  3
     *
     * initial       goal         Manhattan = 10 + 0

     * @return  the manhattan distance
     */
    public int manhattan()
    {
        int manhattan = 0;

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (tiles[i][j] == 0) continue;

                int row = (tiles[i][j] - 1) / N;
                int col = (tiles[i][j] - 1) % N;

                manhattan += (Math.abs(i - row) + Math.abs(j - col));
            }
        }

        return manhattan;
    }

    /**
     * Is this board the goal board?
     *
     * @return  true if the puzzle has been solved, otherwise false
     */
    public boolean isGoal()
    {
        return (hamming() == 0);
    }

    /**
     * A board obtained by exchanging two adjacent blocks (the blank does not
     * count) in the same row.
     *
     * For example, here is a board and its 5 possible twins:
     *
     *     1  3       3  1       1  3       1  3       1  3       1  3
     *  4  2  5    4  2  5    2  4  5    4  5  2    4  2  5    4  2  5
     *  7  8  6    7  8  6    7  8  6    7  8  6    8  7  6    7  6  8
     *
     *   board      twin       twin       twin       twin       twin
     *
     * @return  a twin of the current puzzle board
     */
    public Board twin()
    {
        byte[][] copy = copySquareArray(tiles);

        if (N <= 1) return new Board(copy);

        // Find zero so that we don't exchange with the blank.
        // This looks like a O(dim^2) algorithm, but on average it should finish
        // in O(1).
        int row = 0;
        int col = 0;
        byte value = 0;
        byte lastValue = tiles[0][0];

        // TODO: remove the labels one day
        zerosearch:
        for (row = 0; row < N; row++)
        {
            for (col = 0; col < N; col++)
            {
                value = tiles[row][col];
                // Check col>0 because swap must occur on same row
                if (value != 0 && lastValue != 0 && col > 0) break zerosearch;
                lastValue = value;
            }
        }

        copy[row][col]     = lastValue;
        copy[row][col - 1] = value;

        return new Board(copy);
    }

    /**
     * Copy a two dimensional array of bytes.
     *
     * @param original  array to be copied
     *
     * @return  the duplicated array
     */
    private byte[][] copySquareArray(int[][] original)
    {
        int len = original.length;
        byte[][] copy = new byte[len][len];

        for (int row = 0; row < len; row++)
        {
            assert original[row].length == len;

            for (int col = 0; col < len; col++)
            {
                // Assignment guarantees dim < 128, so casting is safe.
                copy[row][col] = (byte) original[row][col];
            }
        }

        return copy;
    }

    /**
     * Copy a two dimensional array of bytes.
     *
     * @param original  array to be copied
     *
     * @return  the duplicated array
     */
    private byte[][] copySquareArray(byte[][] original)
    {
        int len = original.length;
        byte[][] copy = new byte[len][len];

        for (int row = 0; row < len; row++)
        {
            assert original[row].length == len;

            for (int col = 0; col < len; col++)
            {
                copy[row][col] = original[row][col];
            }
        }

        return copy;
    }


    /**
     * Does this board equal y?
     *
     * @param y
     *
     * @return  true if this Board equals y; false otherwise
     */
    public boolean equals(Object y)
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        if (this.dimension() != that.dimension()) return false;

        for (int i = 0; i < N; ++i)
        {
            for (int j = 0; j < N; ++j)
            {
                if (this.tiles[i][j] != that.tiles[i][j])
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Swap a row on the puzzle board.
     *
     * @param array     board that is to have a row or column swapped
     * @param fromRow   starting row
     * @param fromCol   starting column
     * @param toRow     ending row
     * @param toCol     ending column
     *
     * @return  board after the swap has been made
     */
    private byte[][] swap(byte[][] array,
                          int fromRow,
                          int fromCol,
                          int toRow,
                          int toCol)
    {
        byte[][] copy = copySquareArray(array);
        byte tmp      = copy[toRow][toCol];

        copy[toRow][toCol] = copy[fromRow][fromCol];
        copy[fromRow][fromCol] = tmp;

        return copy;
    }

    /**
     * All neighbouring boards
     *
     * @return
     */
    public Iterable<Board> neighbors()
    {
        Queue<Board> q = new Queue<Board>();

        // Find zero
        int row = 0;
        int col = 0;

        // TODO: remove the labels one day
        zerosearch:
        for (row = 0; row < N; row++)
        {
            for (col = 0; col < N; col++)
            {
                if (tiles[row][col] == 0) break zerosearch;
            }
        }

        // swap up
        if (row > 0) q.enqueue(new Board(swap(tiles, row, col, row - 1, col)));

        // swap down
        if (row < N - 1)
            q.enqueue(new Board(swap(tiles, row, col, row + 1, col)));

        // swap left
        if (col > 0) q.enqueue(new Board(swap(tiles, row, col, row, col - 1)));

        // swap right
        if (col < N - 1) q.enqueue(new Board(swap(tiles, row, col, row, col + 1)));

        return q;
    }

    /**
     * String representation of the board (in the output format specified below)
     *
     * @return  string representation of the puzzle board
     */
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                s.append(String.format("%2d ", tiles[i][j]));
            }

            s.append("\n");
        }

        return s.toString();
    }
}