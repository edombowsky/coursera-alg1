import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class KdTreeTest
{
    private KdTree k;

    @Before
    public void setUp()
    {
        k = new KdTree();
    }

    private Point2D[] getPoints()
    {
        return new Point2D[] {
                new Point2D(0.3, 0.2),
                new Point2D(0.2, 0.2),
                new Point2D(0.1, 0.1),
                new Point2D(0.2, 0.1),
                new Point2D(0.3, 0.3)
        };
    }

    @Test
    public void testEmpty()
    {
        assertEquals(0, k.size());
        assertTrue(k.isEmpty());
    }

    @Test
    public void testInsertFirst()
    {
        k.insert(new Point2D(0.1, 0.1));
        assertEquals(1, k.size());
        assertFalse(k.isEmpty());
    }

    @Test
    public void testInsertDistinct()
    {
        Point2D[] points = getPoints();
        for (Point2D point : points) k.insert(point);
        assertEquals(points.length, k.size());
    }

    @Test
    public void testInsertSame()
    {
        for (int i = 0; i < 5; i++) k.insert(new Point2D(0.1, 0.1));
        assertEquals(1, k.size());
    }

    @Test
    public void testEmptyContains()
    {
        assertFalse(k.contains(new Point2D(0.1, 0.1)));
    }

    @Test
    public void testSingleContains()
    {
        k.insert(new Point2D(0.1, 0.1));
        assertEquals(1, k.size());
        assertTrue(k.contains(new Point2D(0.1, 0.1)));
    }

    @Test
    public void testMultipleContains()
    {
        Point2D[] points = getPoints();
        for (Point2D point : points) k.insert(point);
        for (Point2D point : points) assertTrue(k.contains(point));
    }

    @Test
    public void testNnotEmptyNotContains()
    {
        Point2D[] points = getPoints();
        for (Point2D point : points) k.insert(point);

        assertFalse(k.contains(new Point2D(0.4, 0.4)));
        assertFalse(k.contains(new Point2D(0.0, 0.0)));
    }

    @Test
    public void testEmptyRange()
    {
        Iterator<Point2D> iter = k.range(new RectHV(0, 0, 1, 1)).iterator();
        assertFalse(iter.hasNext());
    }

    @Test
    public void testSingleRange()
    {
        Point2D p = new Point2D(1, 1);
        k.insert(p);

        Iterator<Point2D> iter = k.range(new RectHV(0, 0, 1, 1)).iterator();
        assertTrue(iter.hasNext());
        assertTrue(p.equals(iter.next()));
        assertFalse(iter.hasNext());
    }

    @Test
    public void testMultipleRange() {

        Point2D[] points = getPoints();
        for (Point2D point : points) k.insert(point);

        Iterator<Point2D> iter = k.range(new RectHV(0, 0, 1, 1)).iterator();

        int cnt = 0;
        while (iter.hasNext())
        {
            cnt++;
            Point2D p = iter.next();
            Arrays.asList(points).contains(p);
        }
        assertFalse(iter.hasNext());
        assertEquals(points.length, cnt);
    }

    @Test
    public void testSelectedRange()
    {
        Point2D[] points = new Point2D[]{
                new Point2D(.5, .5),
                new Point2D(.7, .8),
                new Point2D(.1, .1),
                new Point2D(1, 0),
                new Point2D(.3, .9),
        };
        for (Point2D point : points) k.insert(point);
        assertEquals(5, k.size());

        Iterator<Point2D> iter = k.range(new RectHV(.6, .6, .8, .8)).iterator();
        assertTrue(iter.hasNext());
        iter.next().equals(points[1]);
        assertFalse(iter.hasNext());

        iter = k.range(new RectHV(0, 0, .6, .6)).iterator();
        assertTrue(iter.hasNext());
        iter.next().equals(points[2]);
        assertTrue(iter.hasNext());
        iter.next().equals(points[0]);
        assertFalse(iter.hasNext());
    }

    @Test
    public void testNotEmptyNoRange()
    {
        Point2D[] points = getPoints();
        for (Point2D point : points) k.insert(point);

        Iterator<Point2D> iter = k.range(new RectHV(.4, .4, 1, 1)).iterator();
        assertFalse(iter.hasNext());
    }

    @Test
    public void testEmptyNearest()
    {
        assertNull(k.nearest(new Point2D(0, 0)));
    }

    @Test
    public void testNearestSingle()
    {
        Point2D p = new Point2D(0, 0);
        k.insert(p);
        assertTrue(p.equals(k.nearest(new Point2D(1, 1))));
        assertTrue(p.equals(k.nearest(p)));
    }

    @Test
    public void testNearestMultipleExact()
    {
        Point2D[] points = getPoints();
        for (Point2D point : points) k.insert(point);

        for (Point2D point : points) assertTrue(point.equals(k.nearest(point)));
    }

    @Test
    public void testNearestMultiple()
    {
        Point2D[] points = new Point2D[]{
                new Point2D(.5, .5),
                new Point2D(.7, .8),
                new Point2D(.1, .1),
                new Point2D(1, 0),
                new Point2D(.3, .9),
        };
        for (Point2D point : points) k.insert(point);

        assertTrue(points[0].equals(k.nearest(new Point2D(.6, .6))));
        assertTrue(points[3].equals(k.nearest(new Point2D(.6, 0))));
        assertTrue(points[4].equals(k.nearest(new Point2D(0, 1))));
        assertTrue(points[2].equals(k.nearest(new Point2D(.2, .2))));
        assertTrue(points[1].equals(k.nearest(new Point2D(1, 1))));
    }
}