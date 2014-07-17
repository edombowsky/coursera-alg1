/*************************************************************************
 * Name: Earl Dombowsky
 * Email:
 *
 * Compilation: javac Solver.java
 * Execution:
 *
 * Description: An immutable data type for boards of 8Puzzle problem.
 *
 *************************************************************************/

public class Solver
{
    /**
     * Find a solution to the initial board (using the A* algorithm)
     * @param initial
     */
    public Solver(Board initial)
    {
        // TODO: implement this
    }

    /**
     * Is the initial board solvable?
     *
     * @return
     */
    public boolean isSolvable()
    {
        // TODO: implement this
        return true;
    }

    /**
     * Min number of moves to solve initial board; -1 if no solution
     *
     * @return
     */
    public int moves()
    {
        // TODO: implement this
        return 0;
    }

    /**
     * Sequence of boards in a shortest solution; null if no solution
     *
     * @return
     */
//    public Iterable<Board> solution()
//    {
//        // TODO: implement this
//    }

    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException(
                    "Must specify a file path as the first argument!");
        }

        // create initial board from file
        In in = new In(args[0]);

        int N = in.readInt();

        if (N <= 0)
        {
            throw new IllegalArgumentException(
                    "Board dimension must be greater than 0.");
        }

        int[][] blocks = new int[N][N];

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
        {
            StdOut.println("No solution possible");
        }
        else
        {
            StdOut.println("Minimum number of moves = " + solver.moves());
//            for (Board board : solver.solution())
//            {
//                StdOut.println(board);
//            }
        }
    }
}