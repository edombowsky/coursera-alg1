import org.junit.Test;

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

        String expected = "123";//s.toString();
        String actual   = board.toString();

        assertEquals(0, 1);
//        assertThat("this string", is("not this string"));
//        assertThat(actual, is(expected));
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
        int[][] blks1 = new int [][] { { 0, 1, 3} };
        int[][] blks2 = new int [][] { { 0, 1, 3} };

        Board board1 = new Board(blks1);
        Board board2 = new Board(blks2);

        assertTrue("board1 is equal to board2", board1.equals(board2));
    }

    @Test
    public void notEaualBoards()
    {
        int[][] blks1 = new int [][] { { 0, 1, 3} };
        int[][] blks2 = new int [][] { { 0, 1, 4} };

        Board board1 = new Board(blks1);
        Board board2 = new Board(blks2);

        assertFalse("board1 is not equal to board2", board1.equals(board2));
    }

}