import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class PointSETTest
{
    PointSET set;

    @Before
    public void setUp()
    {
        set = new PointSET();
        set.insert(new Point2D(0.1, 0.1));
        set.insert(new Point2D(0.4, 0.1));
        set.insert(new Point2D(0.4, 0.5));
    }

    @Test
    public void testEmpty()
    {
        PointSET s = new PointSET();
        assertEquals(0, s.size());
        assertTrue(s.isEmpty());
    }

    @Test
    public void testNotEmpty()
    {
        assertEquals(3, set.size());
        assertFalse(set.isEmpty());
    }

    @Test
    public void testContains()
    {
        assertTrue(set.contains(new Point2D(0.1, 0.1)));
        assertTrue(set.contains(new Point2D(0.4, 0.1)));
        assertTrue(set.contains(new Point2D(0.4, 0.5)));
    }

    @Test
    public void testNearest()
    {
        assertEquals(new Point2D(0.1, 0.1), set.nearest(new Point2D(0.1, 0.1)));
        assertEquals(new Point2D(0.1, 0.1), set.nearest(new Point2D(0.2, 0.2)));
        assertEquals(new Point2D(0.4, 0.1), set.nearest(new Point2D(0.3, 0.1)));
        assertEquals(new Point2D(0.4, 0.5), set.nearest(new Point2D(0.4, 0.5)));
        assertEquals(new Point2D(0.4, 0.5), set.nearest(new Point2D(0.6, 0.6)));
    }

    @Test
    public void testRangeLowerLeft()
    {
        RectHV rect = new RectHV(0.0, 0.0, 0.2, 0.3);
        Iterator<Point2D> iter = set.range(rect).iterator();
        assertTrue(iter.hasNext());
        assertEquals(new Point2D(0.1, 0.1), iter.next());
        assertFalse(iter.hasNext());

        rect = new RectHV(0.1, 0.1, 0.2, 0.3);
        iter = set.range(rect).iterator();
        assertTrue(iter.hasNext());
        assertEquals(new Point2D(0.1, 0.1), iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testRangeBottom()
    {
        RectHV rect = new RectHV(0.0, 0.0, 0.5, 0.2);
        Iterator<Point2D> iter = set.range(rect).iterator();

        assertTrue(iter.hasNext());
        assertEquals(new Point2D(0.4, 0.1), iter.next());

        assertTrue(iter.hasNext());
        assertEquals(new Point2D(0.1, 0.1), iter.next());

        assertFalse(iter.hasNext());
    }

    @Test
    public void testRangeAll()
    {
        RectHV rect = new RectHV(0.1, 0.1, 0.5, 0.5);
        Iterator<Point2D> iter = set.range(rect).iterator();

        assertTrue(iter.hasNext());
        assertEquals(new Point2D(0.4, 0.5), iter.next());

        assertTrue(iter.hasNext());
        assertEquals(new Point2D(0.4, 0.1), iter.next());

        assertTrue(iter.hasNext());
        assertEquals(new Point2D(0.1, 0.1), iter.next());

        assertFalse(iter.hasNext());
    }

    @Test
    public void testRangeEmpty()
    {
        RectHV rect = new RectHV(0.1, 0.2, 0.3, 0.5);
        Iterator<Point2D> iter = set.range(rect).iterator();
        assertFalse(iter.hasNext());
    }
}