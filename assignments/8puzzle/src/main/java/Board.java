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
    /**
     * Construct a board from an N-by-N array of blocks (where
     * blocks[i][j] = block in row i, column j).
     *
     * @param blocks
     */
    public Board(int[][] blocks)
    {
        // TODO: implement this
    }

    /**
     * Board dimension N
     *
     * @return
     */
    public int dimension()
    {
        // TODO: implement this
        return 1;
    }

    /**
     * Number of blocks out of place
     *
     * @return
     */
    public int hamming()
    {
        // TODO: implement this
        return 0;
    }

    /**
     * Sum of Manhattan distances between blocks and goal
     *
     * @return
     */
    public int manhattan()
    {
        // TODO: implement this
        return 0;
    }

    /**
     * Is this board the goal board?
     *
     * @return
     */
    public boolean isGoal()
    {
        // TODO: implement this
        return true;
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
        int[][] blocks = new int[5][10];

        return new Board(blocks);
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
        // TODO: implement this
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        return true; // (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
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
        // TODO: implement this
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