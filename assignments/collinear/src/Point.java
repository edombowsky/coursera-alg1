/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point>
{
    /**
     * Compare points by slope to this point.
     *
     * Compare points by the slopes they make with the invoking point (x0, y0).
     * Formally, the point (x1, y1) is less than the point (x2, y2) if and only
     * if the slope (y1 − y0) / (x1 − x0) is less than the slope
     * (y2 − y0) / (x2 − x0). Treat horizontal, vertical, and degenerate line
     * segments as in the slopeTo() method.
     */
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>()
    {
        public int compare(Point p, Point q)
        {
            double pqSlope = slopeTo(p) - slopeTo(q);

            if (pqSlope > 0.0)
            {
                return 1;
            }
            else if (pqSlope < 0.0)
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    /**
     * Construct the point (x, y)
     *
     * @param x
     * @param y
     */
    public Point(int x, int y)
    {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Plot this point to standard drawing
     */
    public void draw()
    {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draw line segment between this point and that point to standard drawing
     *
     * @param that
     */
    public void drawTo(Point that)
    {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Slope between this point and that point.
     *
     * The slope between the invoking point (x0, y0) and the argument point (x1, y1),
     * which is given by the formula (y1 − y0) / (x1 − x0). Treat the slope of a
     * horizontal line segment as positive zero; treat the slope of a vertical line
     * segment as positive infinity; treat the slope of a degenerate line segment
     * (between a point and itself) as negative infinity.
     *
     * @param that
     *
     * @return
     */
    public double slopeTo(Point that)
    {
        /* YOUR CODE HERE */
        if (that.x == this.x)
        {
            if (that.y == this.y)
            {
                // Degenerative line segment
                return Double.NEGATIVE_INFINITY;
            }

            // Vertical line segment
            return Double.POSITIVE_INFINITY;
        }

        // Horizontal line segment
        if (this.y == that.y) //return 0.0;
        {
            double a = 1.0;
            return (a - a) / a; //positive zero
        }

        double deltaX = (double) that.x - (double) this.x;
        double deltaY = (double) that.y - (double) this.y;

        return deltaY / deltaX;
    }

    /**
     * Is this point lexicographically smaller than that one?
     *
     * Compares points by their y-coordinates, breaking ties by their x-coordinates.
     * Formally, the invoking point (x0, y0) is less than the argument point (x1, y1)
     * if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that  point to compare to
     *
     * @return a negative value if a < b, 0 if a and b are equal, a positive value if a > b

     */
    public int compareTo(Point that)
    {
        /* YOUR CODE HERE */
        if (this.y < that.y || (this.y == that.y && this.x < that.x))
        {
            return -1;
        }
        else if (this.y == that.y && this.x == that.x)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

    /**
     * String representation of this point
     *
     * @return a string representation of a point
     */
    public String toString()
    {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args)
    {
        /* YOUR CODE HERE */

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        Point p1 = new Point(100, 20042);
        Point p2 = new Point(10, 3003);
        StdOut.println(p1.slopeTo(p2));

        // Horizontal line
        Point p4 = new Point(7453, 14118);
        Point p5 = new Point(2682, 14118);
        StdOut.println("Horizontal line slope: " + p4.slopeTo(p5));

        // Degenerate line segment
        Point p6 = new Point(0, 30000);
        Point p7 = new Point(0, 30000);
        StdOut.println("Degenerate line segment: " + p6.slopeTo(p7));

        // Vertical line
        Point p8 = new Point(14407, 19953);
        Point p9 = new Point(14407, 17831);
        StdOut.println("Vertical line slope: " + p8.slopeTo(p9));

        Point origin = new Point(0, 0);
        Point[] pts = new Point[] {
                new Point(1, 1),
                new Point(1, 0),
                new Point(1, -1),
                new Point(0, 1),
                origin,
                new Point(0, -1),
                new Point(-1, 1),
                new Point(-1, 0),
                new Point(-1, -1)
        };

        Arrays.sort(pts, origin.SLOPE_ORDER);

        for (int i = 0; i < pts.length; ++i)
        {
            StdOut.println(pts[i] +": "+ origin.slopeTo(pts[i]));
        }

        p1.draw();
        p2.draw();
        p4.draw();
        p5.draw();
        p6.draw();
        p7.draw();
        p8.draw();
        p9.draw();

        p1.drawTo(p2);
        p6.drawTo(p7);
        p8.drawTo(p9);
        p4.drawTo(p5);

        // display to screen all at once
        StdDraw.show(0);
    }
}
