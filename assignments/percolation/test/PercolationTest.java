import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Test cases for Percolation
 *
 * @author CAEADOM
 */
public class PercolationTest
        //extends TestCase
{
    @Test
    public void sizeOfOneWorksProperly()
    {
        Percolation perc = new Percolation(1);

        assertFalse(perc.percolates());

        perc.open(1, 1);

        assertTrue(perc.percolates());
    }

    @Test
    public void newPercolationIsAlwaysClosed() {
        Percolation perc = new Percolation(2);

        assertFalse(perc.isOpen(1, 1));
        assertFalse(perc.isOpen(1, 2));
        assertFalse(perc.isOpen(2, 1));
        assertFalse(perc.isOpen(2, 2));
    }

    @Test
    public void createPercolation()
    {
        Percolation perc = new Percolation(13);

        assertFalse(perc.percolates());
        perc.open(3, 3);
        assertTrue(perc.isOpen(3, 3));
    }

    @Test
    public void testIsOpenBoundaries()
    {
        Percolation perc = new Percolation(20);

        perc.open(1, 1);
        perc.open(1, 20);
        perc.open(20, 20);
        perc.open(20, 1);

        assertTrue(perc.isOpen(1, 1));
        assertTrue(perc.isOpen(1, 20));
        assertTrue(perc.isOpen(20, 20));
        assertTrue(perc.isOpen(20, 1));
    }

    @Test
    public void singleCellField()
    {
        Percolation perc = new Percolation(20);

        Percolation p = new Percolation(1);
        p.open(1, 1);

        assertTrue(p.percolates());
    }

    @Test
    public void cellPercalates()
    {
        Percolation perc = new Percolation(1);

        perc.open(1,1);
        assertTrue(perc.isFull(1, 1));
    }

    @Test
    public void testIsFull() throws Exception
    {
        Percolation perc = new Percolation(5);

        perc.open(1, 1);
        perc.open(2, 1);
        perc.open(2, 2);
        perc.open(2, 3);
        perc.open(2, 4);
        perc.open(2, 5);
        perc.open(3, 5);
        perc.open(3, 5);
        perc.open(3, 4);
        perc.open(3, 3);
        perc.open(4, 3);
        perc.open(4, 2);

        assertFalse(perc.percolates());

        perc.open(5, 2);
        assertTrue(perc.percolates());

        assertFalse(perc.isFull(1, 5));
        assertFalse(perc.isFull(1, 2));
        assertTrue(perc.isFull(3, 5));
        assertTrue(perc.isFull(2, 3));
    }

    @Test
    public void testBackwashPrevention(){
        Percolation perc = new Percolation(3);

        perc.open(1, 3);
        perc.open(2, 3);
        perc.open(3, 3);
        perc.open(3, 1);
        assertFalse(perc.isFull(3,1));
    }

    // Check boundary conditions

    @Test(expected = IllegalArgumentException.class)
    public void initialSizeEquals0()
    {
        Percolation perc = new Percolation(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenOutOfBoundsToTheLowEndOfXAxis()
    {
        Percolation perc = new Percolation(20);

        perc.isOpen(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenOutOfBoundsToTheLowEndOfXYAxis()
    {
        Percolation perc = new Percolation(20);

        perc.isOpen(0, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenOutOfBoundsToTheHighEndOfYAxis()
    {
        Percolation perc = new Percolation(20);

        perc.isOpen(1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenOutOfBoundsToTheHighEndOfXAxis()
    {
        Percolation perc = new Percolation(20);

        perc.isOpen(21, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullOutOfBoundsToTheLowEndOfXAxis()
    {
        Percolation perc = new Percolation(20);

        perc.isFull(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullOutOfBoundsToTheLowEndOfXYAxis()
    {
        Percolation perc = new Percolation(20);

        perc.isFull(0, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullOutOfBoundsToTheHighEndOfYAxis()
    {
        Percolation perc = new Percolation(20);

        perc.isFull(1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullOutOfBoundsToTheHighEndOfXAxis()
    {
        Percolation perc = new Percolation(20);

        perc.isFull(21, 1);
    }
}
