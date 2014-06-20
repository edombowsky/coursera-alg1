import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by CAEADOM on 6/19/14.
 */
public class PercolationTest extends TestCase {
    private Percolation percolation;

    @Before
    public void setUp() throws Exception {
        percolation = new Percolation(20);
    }

    @Test
    public void createPercolation() {
        percolation.open(3, 3);
        assertTrue(percolation.isOpen(3, 13));
    }

    @Test
    public void testIsOpenBoundaries() {
        percolation.open(1, 1);
        percolation.open(1, 20);
        percolation.open(20, 20);
        percolation.open(20, 1);

        assertTrue(percolation.isOpen(1, 1));
        assertTrue(percolation.isOpen(1, 20));
        assertTrue(percolation.isOpen(20, 20));
        assertTrue(percolation.isOpen(20, 1));
    }

    @Test
    public void singleCellField() {
        Percolation p = new Percolation(1);
        p.open(1, 1);

        assertTrue(p.percolates());
    }

    @Test
    public void cellPercalates() {
        Percolation p = new Percolation(1);
        p.open(1,1);
        assertTrue(p.isFull(1, 1));
    }

    @Test
    public void testIsFull() throws Exception {
        Percolation p = new Percolation(5);
        p.open(1,1);
        p.open(2,1);
        p.open(2,2);
        p.open(2,3);
        p.open(2,4);
        p.open(2,5);
        p.open(3,5);
        p.open(3,5);
        p.open(3,4);
        p.open(3,3);
        p.open(4,3);
        p.open(4,2);

        assertFalse(p.percolates());

        p.open(5,2);
        assertTrue(p.percolates());

        assertFalse(p.isFull(1, 5));
        assertFalse(p.isFull(1, 2));
        assertTrue(p.isFull(3, 5));
        assertTrue(p.isFull(2, 3));
    }

    @Test
    public void testPercolates() throws Exception {

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkThrows01() {
        percolation.isOpen(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkThrows() {
        percolation.isOpen(0, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkThrows10() {
        percolation.isOpen(1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkThrows211() {
        percolation.isOpen(21, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkThrows121() {
        percolation.isOpen(1, 21);
    }
}
