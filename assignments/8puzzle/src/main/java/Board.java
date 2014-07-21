import java.util.Arrays;
import java.util.NavigableMap;

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
    private int N;
    private byte[][] tiles = null;

    /**
     * Construct a board from an N-by-N array of blocks (where
     * blocks[i][j] = block in row i, column j).
     *
     * @param blocks
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
     * Number of blocks out of place
     *
     * @return
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
     * @return
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
     * @return
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
     * @return
     */
    public Board twin()
    {
        // TODO: implement this

        int[][] newTiles = copyArray();

        for (int i = 0; i < dimension(); i++)
        {
            if (newTiles[i][0] == 0 || newTiles[i][1] == 0)
            {
                continue;
            }

            newTiles[i][0] = this.tiles[i][1];
            newTiles[i][1] = this.tiles[i][0];

            break;
        }

        return new Board(newTiles);
    }

    /**
     * Copy a two dimensional array of bytes.
     *
     * @return  a copy of the tiles array
     */
    private int[][] copyArray()
    {
        int[][] result = new int[N][];

        for (int i = 0; i < N; i++)
        {
            assert tiles[i].length == N;
            result[i] = new int[tiles[i].length];

            System.arraycopy(tiles[i], 0, result[i], 0, tiles[i].length);
        }

        return result;
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
     * All neighboring boards
     *
     * @return
     */
//    public Iterable<Board> neighbors()
//    {
//        // TODO: implement this
//    }

    /**
     * String representation of the board (in the output format specified below)
     *
     * @return
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