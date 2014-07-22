import java.util.Comparator;

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
    private static final Comparator<Node> MANHATTAN = new ManhattanOrder();

    // priority queue to save all the possible options
    private MinPQ<Node> pq = new MinPQ<Node>(MANHATTAN);

    // priority queue for the twin board
    private MinPQ<Node> pq2 = new MinPQ<Node>(MANHATTAN);

    // Current search node
    private Node cur;

    // Current search node of twin board
    private Node cur2;

    // Solvable?
    private boolean solvable;

    /**
     * Search nodes for each step
     */
    private class Node implements Comparable<Node>
    {
        private Board board;
        private Node parent;
        private int move;
        private int priority;

        public Node(Board b, Node p, int m)
        {
            board = b;
            parent = p;
            move = m;
            priority = b.manhattan() + m;
        }

        public int compareTo(Node other)
        {
            if (this.priority < other.priority) return -1;
            if (this.priority > other.priority) return 1;

            return 0;
        }
    }

    private static class ManhattanOrder implements Comparator<Node>
    {
        public int compare(Node v, Node w)
        {
            if (v.priority < w.priority)
            {
                return -1;
            }
            else if (v.priority > w.priority)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }

    /**
     * Find a solution to the initial board (using the A* algorithm).
     *
     * @param initial  the puzzle board to solve
     */
    public Solver(Board initial)
    {
        cur  = new Node(initial, null, 0);
        cur2 = new Node(initial.twin(), null, 0);

        pq.insert(cur);
        pq2.insert(cur2);

        while (!cur.board.isGoal() && !cur2.board.isGoal())
        {
            cur = pq.delMin();
            cur2 = pq2.delMin();

            for (Board i: cur.board.neighbors())
            {
                if (cur.parent == null || !i.equals(cur.parent.board))
                {
                    pq.insert(new Node(i, cur, cur.move+1));
                }
            }

            for (Board i: cur2.board.neighbors())
            {
                if (cur2.parent == null || !i.equals(cur2.parent.board))
                {
                    pq2.insert(new Node(i, cur2, cur2.move+1));
                }
            }
        }

        if (cur.board.isGoal())
            solvable = true;
        else
            solvable = false;
    }

    /**
     * Is the initial board solvable?
     *
     * @return  true if the puzzle board is solvable, false otherwise
     */
    public boolean isSolvable()
    {
        return solvable;
    }

    /**
     * Min number of moves to solve initial board; -1 if no solution
     *
     * @return  number of moves to solve puzzle board, -1 if there is no solution
     */
    public int moves()
    {
        if (solvable) return cur.move;

        return -1;
    }

    /**
     * Sequence of boards in a shortest solution; null if no solution
     *
     * @return
     */
    public Iterable<Board> solution()
    {
        if (!solvable) return null;

        Stack<Board> q = new Stack<Board>();
        Node i = cur;

        while (i != null)
        {
            q.push(i.board);
            i = i.parent;
        }

        return q;
    }

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
            throw new IndexOutOfBoundsException(
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
            for (Board board : solver.solution())
            {
                StdOut.println(board);
            }
        }
    }
}