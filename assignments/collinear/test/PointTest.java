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
}