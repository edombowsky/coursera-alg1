import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BoardTest
{
    @Test
    public void testToString()
    {
        int[][] myBlocks = new int [][] { { 0, 1, 3},
                { 4, 2, 5},
                { 7, 8, 6}};
        Board board = new Board(myBlocks);

        StringBuilder s = new StringBuilder();
        s.append(3 + "\n");
        s.append(String.format("%2d ", 0));
        s.append(String.format("%2d ", 1));
        s.append(String.format("%2d ", 3));
        s.append("\n");
        s.append(String.format("%2d ", 4));
        s.append(String.format("%2d ", 2));
        s.append(String.format("%2d ", 5));
        s.append("\n");
        s.append(String.format("%2d ", 7));
        s.append(String.format("%2d ", 8));
        s.append(String.format("%2d ", 6));
        s.append("\n");

        String expected = s.toString();
        String actual   = board.toString();

        assertEquals(actual, expected);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void dimensionToSmall()
    {
        int[][] myBlocks = new int [][] { { 0, 1, 3} };

        Board board = new Board(myBlocks);
    }

    @Test
    public void eaualBoards()
    {
        int[][] tiles1 = new int [][] { { 0, 1, 3}, { 0, 1, 3} };
        int[][] tiles2 = new int [][] { { 0, 1, 3}, { 0, 1, 3} };

        Board board1 = new Board(tiles1);
        Board board2 = new Board(tiles2);

        assertTrue("board1 is equal to board2", board1.equals(board2));
    }

    @Test
    public void notEaualBoards()
    {
        int[][] tiles1 = new int [][] { { 0, 1}, { 2, 3} };
        int[][] tiles2 = new int [][] { { 0, 1}, { 2, 4} };

        Board board1 = new Board(tiles1);
        Board board2 = new Board(tiles2);

        assertFalse("board1 is not equal to board2", board1.equals(board2));
    }

    @Test
    public void hamming()
    {
        int[][] tiles = new int [][] { { 8, 1, 3}, { 4, 0, 2}, {7, 6, 5} };
        Board board   = new Board(tiles);

        int expected = 5;
        int actual   = board.hamming();

        assertThat(actual, is(expected));
    }

    @Test
    public void manhattan()
    {
        int[][] tiles = new int [][] { { 8, 1, 3}, { 4, 0, 2}, {7, 6, 5} };
        Board board  = new Board(tiles);

        int expected = 10;
        int actual   = board.manhattan();

        assertThat(actual, is(expected));
    }

    @Test
    public void isNotGoalBoard()
    {
        int[][] tiles1 = new int [][] { { 8, 1, 3}, { 4, 0, 2}, {7, 6, 5} };
        Board board = new Board(tiles1);

        boolean expected = false;
        boolean actual   = board.isGoal();

        assertThat(actual, is(expected));
    }

    @Test
    public void isGoalBoard()
    {
        int[][] tiles1 = new int [][] { { 1, 2, 3}, { 4, 5, 6}, {7, 8, 0} };
        Board board = new Board(tiles1);

        boolean expected = true;
        boolean actual   = board.isGoal();

        assertThat(actual, is(expected));
    }
}