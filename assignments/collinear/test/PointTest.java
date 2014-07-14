import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class PointTest
{

    @Test
    public void testToString()
    {
        Point point = new Point(1, 2);

        assertThat(point.toString(), is("(1, 2)"));
    }

    @Test
    public void testCompareTo()
    {
        Point p1 = new Point(-100, 20042);
        Point p2 = new Point(10, 3003);

        assertThat(p1.compareTo(p2), is(1));
    }

    @Test
    public void degenerativeLineSegment()
    {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);
        assertThat(p1.slopeTo(p2), is(Double.NEGATIVE_INFINITY));
        assertThat(p1.slopeTo(p1), is(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void verticalLineSegment()
    {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);

        assertThat(p1.slopeTo(p2), is(Double.POSITIVE_INFINITY));
    }

    @Test
    public void commutativeSlopeTo()
    {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);

        assertThat(p1.slopeTo(p2), is(p2.slopeTo(p1)));
    }

    @Test
    public void horizontalLineSegment()
    {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);

        assertThat(p1.slopeTo(p2), is(Double.POSITIVE_INFINITY));
    }

    @Test
    public void testSlopeOrder()
    {
        Point p = new Point(1, 1);
        Point q = new Point(1, 2);
        Point r = new Point(2, 1);
        assertThat(p.SLOPE_ORDER.compare(q, r), is(1));
    }
}